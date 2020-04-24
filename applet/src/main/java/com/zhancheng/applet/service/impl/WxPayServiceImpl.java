package com.zhancheng.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.zhancheng.applet.service.WxPayService;
import com.zhancheng.core.commom.MapFactory;
import com.zhancheng.core.config.award.AwardConfig;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.wx.WxConfig;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.entity.*;

import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.util.RewardsUtils;
import com.zhancheng.core.util.WxPayUtil;
import com.zhancheng.core.util.WxUtil;
import com.zhancheng.core.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.zhancheng.core.constant.Constant.AWARD_STATE_DAIJIESUAN;

/**
 * @author BianShuHeng
 * @decription
 * @project WxPayServiceImpl
 * @date 2019/11/8 17:37
 */
@Service
public class WxPayServiceImpl implements WxPayService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderProductMapper orderProductMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private WxPayUtil wxPayUtil;

    @Autowired
    private WxUtil wxUtil;

    @Resource
    private WxConfig wxConfig;

    @Resource
    private RewardsUtils rewardsUtils;

    @Resource
    private AwardMapper awardMapper;
    @Resource
    private AwardWayMapper awardWayMapper;
    @Resource
    private AwardConfig awardConfig;

    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Resource
    private UserCloudProductMapper userCloudProductMapper;

    @Resource
    private AgentLevelProductAwardMapper agentLevelProductAwardMapper;

    private static Logger log = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> agentWxPay(Integer orderId, Integer uid) throws Exception {

        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq("id", orderId).eq("user_id", uid));

        if (ObjectUtil.isNull(order)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }

        User user = userMapper.selectById(uid);

        if (ObjectUtil.isNull(user)) {
            throw new MyException(CodeMsg.USER_NOT_EXISTED);
        } else {
            if (StrUtil.isBlank(user.getOpenid())) {
                throw new MyException(CodeMsg.OPENID_IS_NULL);
            }
        }

        // 商户订单号
        String outTradeNo = order.getOrderNumber();

        if (StrUtil.isBlank(outTradeNo)) {
            throw new MyException(CodeMsg.ORDER_NUMBER_ERROR);
        }

        String body = "购买商品支付";
        String attach = "{\"type\":" + 1 + "}";
        // 总金额
        // todo
        String totalFee = order.getPayMoney().multiply(new BigDecimal(100)).intValue() + "";
        String openid = user.getOpenid();
        Map<String, String> result = wxPayUtil.unifiedorder(body, outTradeNo, attach, totalFee, openid);

        if ("FAIL".equals(result.get("status"))) {
            log.error("微信支付失败,请联系管理员{}", result);
        } else {
            log.info("微信支付返回结果{}", result);
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String wxPayUnifiedNotify(String notifyXml) {

        synchronized (new Object()) {
            try {
                Map<String, String> notify = WXPayUtil.xmlToMap(notifyXml);
                String orderNumber = notify.get("out_trade_no");
                String appId = notify.get("appid");
                String totalFee = notify.get("total_fee");
                String attach = notify.get("attach");
                // 交易编号
                String transactionId = notify.get("transaction_id");

                log.info("totalFee:\t" + totalFee);

                JSONObject json = JSONUtil.parseObj(attach);
                String type = json.getStr("type");
                log.info("type:\t" + type);
                // 支付金额

                        log.info("*****1*****");
                        Order order = orderMapper.selectOne(new QueryWrapper<Order>()
                                .eq("order_number", orderNumber)
                                .eq("is_deleted", 0));
                        log.info("*****2:\t" + orderNumber);
                        //判断订单号是否正确
                        if (ObjectUtil.isNull(order)) {
                            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[商户订单不存在]]></return_msg>" + "</xml>";
                        }
                        log.info("*****3*****");
                        //判断订单是否是待付款状态(订单状态：-1.已删除0.已关闭1.待确认2.待付款3.待总部处理4.待发货5.待收货6.待评价7.已完成)
                        if (ObjectUtil.isNull(order.getOrderState()) || order.getOrderState() != OrderStateEnum.AWAIT_PAYMENT.getState()) {
                            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[订单状态异常,可能已经支付]]></return_msg>" + "</xml>";
                        }
                        log.info("*****4*****");
                        // 数据库查出来的金钱
                        int intValue = order.getPayMoney().multiply(new BigDecimal("100")).intValue();
                        //判断金额是否一致
                        if (!totalFee.equals(String.valueOf(intValue))) {
                            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[订单金额不一致]]></return_msg>" + "</xml>";
                        }
                        log.info("*****5*****");
                        //判断返回码是否是SUCCESS
                        if ("SUCCESS".equals(notify.get("return_code")) && "SUCCESS".equals(notify.get("result_code"))) {
                            //验证签名
                            log.info("*****6*****");
                            if (WXPayUtil.isSignatureValid(notify, wxConfig.getKey())) {
                                // 修改订单状态

                                order.setOrderState(OrderStateEnum.AWAIT_SEND_OUT.getState())
                                        .setPayTime(new Date()).setPayNumber(transactionId).setPayWay(1);
                                order.updateById();
                                log.info("*****66***** user_id:"+order.getUserId());
                                // todo: 分配奖励
                                List<OrderProductVO> orderProduct = orderProductMapper.queryList(order.getId());
                                log.info("*****666***** order_id:"+order.getId());
                                //给购买用户的永久唯一邀请人发放奖励
                                 UserVO userVO = userMapper.queryInfoById(order.getUserId());//查邀请人id
                                if(userVO.getInviterId()==null || userVO.getInviterId()==0){
                                    log.info("用户"+userVO.getNickname()+"没有邀请人"+userVO.getInviterId());
                                     return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml>";
                                 }
                                UserVO shareUserVO = userMapper.queryInfoById(userVO.getInviterId());//查邀请人
                                OrderProductVO op;
                                log.info("*****888*****");
                                double total = 0;
                                //计算总价格
                                for(int i=0;i<orderProduct.size();i++){
                                    total+=(orderProduct.get(i).getProductNum()*orderProduct.get(i).getProductPrice().doubleValue());
                                    total+=order.getDiscount().doubleValue();//加上优惠金额才是总金额
                                }
                                AgentLevel agentLevel = agentLevelMapper.selectById(userVO.getAgentLevel());
                                //查询出所有代理等级
                                List<AgentLevel> agentLevelList = agentLevelMapper.selectList(null);
                                for(int i=0;i<agentLevelList.size();i++){
                                    if(agentLevelList.get(i).getLevelInt()<=agentLevel.getLevelInt())continue;//代理列表等级低于现有等级，不操作
                                    //总价小于代理最低进货价，不自动升级
                                    if(total<agentLevelList.get(i).getMinStock().doubleValue()) continue;
                                    userVO.setAgentLevel(agentLevelList.get(i).getId());//自动升级，方便下面计算佣金
                                    log.info("*****升级后的等级*****"+userVO.getAgentLevel());
                                }
                                log.info("*****999*****");
                                for(int a=0;a<orderProduct.size();a++){
                                    double m=0;
                                    op = orderProduct.get(a);
                                   // if(op.getShareId()==0)continue;
                                    //给订单中每一个产品的分享人发放奖励
                                    //User user = userMapper.selectById(op.getShareId());
                                    //int award_share_id =user.getInviterId();

                                    //如果分享人有该产品云库存，则云库存数量-1，付款金额转入佣金
                                    UserCloudProductVO userCloudProduct= userCloudProductMapper.queryInfoByProductIdAndUserId(shareUserVO.getId(),op.getPid());
                                    if(userCloudProduct!=null && userCloudProduct.getProductNum()>0){
                                        UserCloudProduct cloudProduct = new UserCloudProduct();
                                        if(userCloudProduct.getProductNum()>=op.getProductNum()){
                                            log.info("*****998*****");
                                            //如果云库存该产品数量>=订单产品数量
                                            BeanUtil.copyProperties(userCloudProduct, cloudProduct);
                                            cloudProduct.setId(userCloudProduct.getId());
                                            cloudProduct.setProductNum(cloudProduct.getProductNum()-op.getProductNum());
                                            cloudProduct.updateById();//修改云库存该产品数量
                                            m+=op.getProductNum().doubleValue()*op.getProductPrice().doubleValue();
                                        }else{
                                            log.info("*****9987*****");
                                            //云库存该产品数量小于订单产品数量
                                            BeanUtil.copyProperties(userCloudProduct, cloudProduct);
                                            m+=cloudProduct.getProductNum().doubleValue()*op.getProductPrice().doubleValue();
                                            cloudProduct.deleteById();//删除云库存该产品
                                        }
                                    }else{
                                        log.info("*****1000*****");
                                        //非云库存产品才有奖励
                                        //总代邀请总代没有奖励
                                        if(userVO.getAgentLevel()==1 && userVO.getAgentLevel()==shareUserVO.getAgentLevel()){
                                            //总代邀请总代没有奖励
                                            log.info("*****1001 总代邀请总代没有奖励*****");
                                        }else{
                                            /*
                                        每个产品按代理等级对应的奖励金额或比例奖励
                                        */
                                            log.info("*****1002*****");
                                            AgentLevelProductAward pa = agentLevelProductAwardMapper.selectOne(new QueryWrapper<AgentLevelProductAward>()
                                                    .eq("agent_level_id", shareUserVO.getAgentLevel()).eq("product_id", op.getPid()));
                                            if(pa!=null){
                                                if(pa.getType()==0){
                                                    //按佣金金额发放奖励
                                                    m += op.getProductNum()*pa.getAwardMoney().doubleValue();
                                                    log.info("*****1003 m:"+m+"*****");
                                                }else if(pa.getType()==1){
                                                    //按佣金比例
                                                }

                                            }else{
                                                log.info("****[该代理等级没有设置该产品的佣金金额]代理等级:"+userVO.getAgentLevel()+" 产品id:"+op.getPid());
                                            }
                                        }

                                    }
                                    log.info("****m:"+m);

                                    if(m<=0)continue;//佣金金额为0
                                    Award award = new Award();
                                    award.setUserId(order.getUserId());
                                    award.setShareId(userVO.getInviterId());
                                    award.setAwardState(AWARD_STATE_DAIJIESUAN);
                                    award.setOrderProductId(op.getId());
                                    award.setAwardMoney(new BigDecimal(m));
                                    awardMapper.insert(award);
                                    log.info("m:"+m);
                                    /*
                                    所有产品按统一比例奖励
                                    AgentLevel agentLevel = agentLevelMapper.selectById(user.getAgentLevel());
                                    if(agentLevel!=null && agentLevel.getAwardPercent().doubleValue()>0){
                                        Award award = new Award();
                                        award.setUserId(order.getUserId());
                                        award.setShareId(op.getShareId());
                                        award.setAwardState(AWARD_STATE_DAIJIESUAN);

                                        award.setOrderProductId(op.getId());
                                            m += op.getProductPrice().doubleValue()*op.getProductNum()*(agentLevel.getAwardPercent().doubleValue()/100.00);
                                            log.info("m:"+m);
                                            award.setAwardMoney(new BigDecimal(m));
                                            log.info("m2:"+award.getAwardMoney());
                                            awardMapper.insert(award);
                                    }
                                    */


                                }


                                //rewardsUtils.allotReward(order);
                                log.info("*****7*****");
                                return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml>";
                            }
                        }



                //判断AppId是否正确
                if (!appId.equals(wxConfig.getAppID())) {
                    return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[app_id不是该商户本身]]></return_msg>" + "</xml>";
                }

            } catch (Exception ex) {
                log.info("回调异常："+ex.getMessage());
                log.info(ex.getMessage());
                log.info(ex.getLocalizedMessage());
                return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[产生异常.]]></return_msg>" + "</xml>";
            }
        }
        return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[支付失败.]]></return_msg>" + "</xml>";
    }

    @Override
    public Map<String, String> wxPay(String orderNumber, Integer uid) throws Exception {
        log.info("wxPay orderNumber:"+orderNumber+" uid:"+uid);
        Map<String, Object> query = MapFactory.getHashMap();
        User user = userMapper.selectById(uid);
        //获取openid
        String openid = user.getOpenid();
        query.put("uid", uid);
        query.put("order_number", orderNumber);
        query.put("order_state", 0);

        OrderVO order = orderMapper.selectOneByOrderNumber(orderNumber);

        if (order == null) {
            throw new MyException(CodeMsg.ORDER_NUMBER_ERROR);
        }

        int price = order.getPayMoney().multiply(new BigDecimal(100)).intValue();
        Map<String, String> map = wxUtil.unifiedOrder("商品支付", "", orderNumber, price + "", openid);
        if ("FAIL".equals(map.get("status"))) {
            log.info("支付失败FAIL ："+map.get("message"));
            throw new MyException(CodeMsg.WXPAY_IS_FAIL);
        }
        map.put("orderId",order.getId()+"");
        return map;
    }

}
