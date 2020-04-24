package com.zhancheng.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.OrderProductService;
import com.zhancheng.applet.service.OrderService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.Constant;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.dto.OrderDTO;
import com.zhancheng.core.dto.OrderProductDTO;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.util.NumberUtil;
import com.zhancheng.core.util.OrderUtil;
import com.zhancheng.core.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Resource
    private OrderProductMapper orderProductMapper;
    @Resource
    private OrderProductService orderProductService;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private OrderUtil orderUtil;

    @Resource
    private UserCloudProductMapper userCloudProductMapper;
    @Resource
    private AwardMapper awardMapper;
    @Resource
    private WalletMapper walletMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Resource
    private AgentLevelProductAwardMapper agentLevelProductAwardMapper;

    @Resource
    private OrderLogisticMapper orderLogisticMapper;

    @Override
    public IPage<OrderListVO> queryPage(PageDto<Order> pageDto, OrderQueryDTO orderQueryDto) {
        if(orderQueryDto.getUserId()==null || "".equals(orderQueryDto.getUserId()) || orderQueryDto.getUserId()==0){
            throw new MyException(CodeMsg.NULL_POINTER_EXCEPTION);
        }
        IPage<OrderListVO> orderListPage = baseMapper.queryPage(pageDto.getPage(), orderQueryDto);

        List<OrderListVO> records = orderListPage.getRecords();

        if (ObjectUtil.isNotEmpty(records)) {
            for (OrderListVO orderListVO : records) {
                List<OrderProductVO> orderProductList = orderProductMapper.queryList(orderListVO.getId());
                orderListVO.setOrderProductList(orderProductList);

                if(orderListVO.getOrderState()>Constant.OrderStateWaitSend){
                    //已发货的订单获取物流信息，可能有多个物流
                    List<OrderLogisticVO> orderLogisticList = orderLogisticMapper.queryList2(orderListVO.getId());
                    orderListVO.setOrderLogisticVOList(orderLogisticList);
                }
            }
        }
        return orderListPage;
    }

    @Override
    public OrderVO queryInfo(Integer id) {

        OrderVO orderVo = baseMapper.queryInfo(id);

        if (ObjectUtil.isNull(orderVo)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }
        List<OrderProductVO> orderProductList = orderProductMapper.queryList(id);
        orderVo.setOrderProductVOList(orderProductList);

        if(orderVo.getOrderState()>Constant.OrderStateWaitSend){
            //已发货的订单获取物流信息，可能有多个物流
            List<OrderLogisticVO> orderLogisticList = orderLogisticMapper.queryList2(id);
            orderVo.setOrderLogisticVOList(orderLogisticList);
        }


        return orderVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(OrderDTO orderDto) {

        // 添加订单信息
        Order order = new Order();
        BeanUtil.copyProperties(orderDto, order);
        String orderNumber = NumberUtil.generateOrderNumber();
        order.setOrderNumber(orderNumber);
        order.setFreight(orderDto.getFreight());
        order.insert();

        List<OrderProductDTO> orderProductDTOList = orderDto.getOrderProductDTOList();

        // totalPrice = orderUtil.addOrderProduct(orderProductDTOList, order.getId(), order.getUserId());
        double total = 0;
        //计算总价格
        for(int i=0;i<orderProductDTOList.size();i++){
            total+=(orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue());
        }


        UserVO userVO = userMapper.queryInfo(orderDto.getUserId());
        AgentLevel agentLevel = agentLevelMapper.selectById(userVO.getAgentLevel());
        //查询出所有代理等级
        List<AgentLevel> agentLevelList = agentLevelMapper.selectList(null);
        for(int i=0;i<agentLevelList.size();i++){
            if(agentLevelList.get(i).getLevelInt()<=agentLevel.getLevelInt())continue;//代理列表等级低于现有等级，不操作
            //总价小于代理最低进货价，不自动升级
            if(total<agentLevelList.get(i).getMinStock().doubleValue()) continue;
            userVO.setAgentLevel(agentLevelList.get(i).getId());//自动升级，方便下面技术代理价格
        }
        BigDecimal discount = new BigDecimal("0");
        //检查用户代理等级对应的代理价格
        for(int i=0;i<orderProductDTOList.size();i++){
            //根据代理等级和产品id查询代理价格
            AgentLevelProductAward pa = agentLevelProductAwardMapper.queryInfo(userVO.getAgentLevel(),orderProductDTOList.get(i).getProductId());
            if (ObjectUtil.isNull(pa)) {
                //没有代理价格，不操作
                continue;
            }
            //优惠金额=原价-代理价格
            discount = discount.add(new BigDecimal((orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue())-(orderProductDTOList.get(i).getProductNum()*pa.getAgentPrice().doubleValue())));
           // discount+=(orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue())-(orderProductDTOList.get(i).getProductNum()*pa.getAgentPrice().doubleValue());
            //有代理价格，按代理价格支付
            orderProductDTOList.get(i).setProductPrice(pa.getAgentPrice());

        }
        // 添加产品订单并计算总价格
        BigDecimal totalPriceNew = orderUtil.addOrderProduct(orderProductDTOList, order.getId(), order.getUserId());
        BigDecimal payMoney = totalPriceNew.add(orderDto.getFreight());
        log.info("totalPriceNew:"+totalPriceNew+" payMoney:"+payMoney+" freight:"+orderDto.getFreight());
        // 修改订单总价格(加上运费后)
        order.setTotalPrice(totalPriceNew).setPayMoney(payMoney).setFreight(orderDto.getFreight());
        order.setDiscount(discount);
        order.setFreight(orderDto.getFreight());
        order.updateById();
        return order.getOrderNumber();
    }

    @Override
    public Boolean cancel(Integer orderId) {
        Order order = baseMapper.selectById(orderId);
        if (ObjectUtil.isNull(order)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }

        order.setOrderState(OrderStateEnum.CLOSE.getState());

        return order.updateById();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean changeToCloudStore(Integer orderId, Integer userId) {
        log.info("changeToCloudStore orderId:"+orderId+" userId:"+userId);
        Order order = baseMapper.selectById(orderId);
        if (ObjectUtil.isNull(order)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }
        if (ObjectUtil.isNull(order.getOrderState()!=OrderStateEnum.AWAIT_SEND_OUT.getState())) {
            throw new MyException(CodeMsg.ORDER_ERROR);
        }
        User user = userMapper.selectById(order.getUserId());
        if(ObjectUtil.isNull(user))throw new MyException(CodeMsg.USER_NOT_EXISTED);

        List<OrderProductVO> orderProductList = orderProductMapper.queryList(orderId);
        OrderProductVO op;
        for(int i=0;i<orderProductList.size();i++){
            op = orderProductList.get(i);
            //查询用户云库存中
            UserCloudProductVO userCloudProduct = userCloudProductMapper.queryInfoByProductIdAndUserId(user.getId(),op.getPid());
            if(userCloudProduct!=null){
               // log.info("云库存产品"+userCloudProduct.getProductId()+" 旧数量："+userCloudProduct.getProductNum());
                userCloudProduct.setProductNum(userCloudProduct.getProductNum()+op.getProductNum());
               // log.info("云库存产品"+userCloudProduct.getProductId()+" 新数量："+userCloudProduct.getProductNum());
                UserCloudProduct cloudProduct = userCloudProductMapper.selectById(userCloudProduct.getId());
                cloudProduct.setProductNum(userCloudProduct.getProductNum());
                cloudProduct.updateById();
            }else{
                UserCloudProduct cloudProduct = new UserCloudProduct();
                cloudProduct.setProductNum(op.getProductNum());
                cloudProduct.setProductId(op.getPid());
                cloudProduct.setProductPrice(op.getProductPrice());
                cloudProduct.setUserId(user.getId());
                cloudProduct.insert();
            }
        }
        //增加订单转入云库存记录
        OrderCloudProductHistory orderCloudProductHistory = new OrderCloudProductHistory();
        orderCloudProductHistory.setUserId(user.getId());
        orderCloudProductHistory.setOrderId(orderId);
        orderCloudProductHistory.setNote("转入前订单状态为"+order.getOrderState());
        orderCloudProductHistory.insert();
        //退回订单运费
        //将运费转入购买人的钱包余额
        Wallet wallet = walletMapper.selectOne(new QueryWrapper<Wallet>()
                .eq("user_id", user.getId()));
        if (ObjectUtil.isNull(wallet)) {
            throw new MyException(CodeMsg.USER_WALLET_NOT_EXISTED);
        }
        wallet.setBalance(new BigDecimal(wallet.getBalance().doubleValue()+order.getFreight().doubleValue()));
        wallet.updateById();
        //钱包余额变动要添加明细记录
        //添加钱包收支明细
        WalletHistory walletHistory = new WalletHistory();
        walletHistory.setWalletId(wallet.getId());
        walletHistory.setUserId(wallet.getUserId());
        walletHistory.setAmount(order.getFreight());
        walletHistory.setBalance(wallet.getBalance());
        walletHistory.setTradeNo(order.getOrderNumber());
        walletHistory.setInOut(Constant.WalletHistoryType_In);
        walletHistory.setTradeType(Constant.WalletHistoryTradeType_Share);
        walletHistory.setTradeDesc("[运费退回]-订单"+order.getOrderNumber()+"转入云库存退运费");
        walletHistory.insert();
        //完成订单
        finishOrder(orderId,userId);
        /*
        //将订单设置已完成
        order.setOrderState(OrderStateEnum.COMPLETED.getState());
        order.updateById();

        //结算订单分享人佣金
        //查询出订单相关产品
        for(int i=0;i<orderProductList.size();i++){
            op = orderProductList.get(i);
            if(op.getShareId()==0)continue;//该订单产品没有分享人
            //结算订单分享人的佣金
            Award award = awardMapper.queryInfoByOpid(order.getUserId(),op.getShareId(),op.getId());
            if (ObjectUtil.isNull(award)) {
                log.info("该订单产品没有分享人");
                continue;
            }
            if(award.getAwardState().equals(Constant.AWARD_STATE_DAIJIESUAN)){
                //原状态为待结算，现改为已完成
                award.setAwardState(Constant.AWARD_STATE_FINISH);
                award.updateById();
                //将佣金转入分享人的钱包余额
                Wallet wallet = walletMapper.selectOne(new QueryWrapper<Wallet>()
                        .eq("user_id", op.getShareId()));
                if (ObjectUtil.isNull(wallet)) {
                    throw new MyException(CodeMsg.USER_WALLET_NOT_EXISTED);
                }
                wallet.setBalance(new BigDecimal(wallet.getBalance().doubleValue()+award.getAwardMoney().doubleValue()));
                wallet.updateById();
                //钱包余额变动要添加明细记录
                //添加钱包收支明细
                WalletHistory walletHistory = new WalletHistory();
                walletHistory.setWalletId(wallet.getId());
                walletHistory.setUserId(wallet.getUserId());
                walletHistory.setAmount(award.getAwardMoney());
                walletHistory.setBalance(wallet.getBalance());
                walletHistory.setTradeNo(order.getOrderNumber()+"_"+op.getId());
                walletHistory.setInOut(Constant.WalletHistoryType_In);
                walletHistory.setTradeType(Constant.WalletHistoryTradeType_Share);
                walletHistory.setTradeDesc("[分享佣金]-"+user.getNickname()+"购买产品"+op.getTitle());
                walletHistory.insert();
            }
        }


        */
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean finishOrder(Integer orderId, Integer userId) {
        Order order = baseMapper.selectById(orderId);
        if (ObjectUtil.isNull(order)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }
        User user = userMapper.selectById(userId);
        if(ObjectUtil.isNull(user))throw new MyException(CodeMsg.USER_NOT_EXISTED);
        //修改订单状态
        changeOrderState(orderId, userId, OrderStateEnum.COMPLETED.getState());
        //查询订单的推荐人佣金列表
        if(user.getInviterId()==0){
            //没有推荐人直接返回
            return true;
        }
        Integer shareId = user.getInviterId();
        //查询出订单相关产品
        List<OrderProductVO> orderProductList = orderProductMapper.queryList(orderId);
        OrderProductVO op;
        for(int i=0;i<orderProductList.size();i++){
            op = orderProductList.get(i);
           // if(op.getShareId()==0)continue;//该订单产品没有分享人
            //结算订单分享人的佣金
            Award award = awardMapper.queryInfoByOpid(order.getUserId(),shareId,op.getId());
            if (ObjectUtil.isNull(award)) {
                log.info("该订单产品没有分享人");
                continue;
            }
            if(award.getAwardState().equals(Constant.AWARD_STATE_DAIJIESUAN)){
                //原状态为待结算，现改为已完成
                award.setAwardState(Constant.AWARD_STATE_FINISH);
                award.updateById();
                //将佣金转入分享人的钱包余额
                Wallet wallet = walletMapper.selectOne(new QueryWrapper<Wallet>()
                        .eq("user_id", shareId));
                if (ObjectUtil.isNull(wallet)) {
                    throw new MyException(CodeMsg.USER_WALLET_NOT_EXISTED);
                }
                wallet.setBalance(new BigDecimal(wallet.getBalance().doubleValue()+award.getAwardMoney().doubleValue()));
                wallet.updateById();
                //钱包余额变动要添加明细记录
                //添加钱包收支明细
                WalletHistory walletHistory = new WalletHistory();
                walletHistory.setWalletId(wallet.getId());
                walletHistory.setUserId(wallet.getUserId());
                walletHistory.setAmount(award.getAwardMoney());
                walletHistory.setBalance(wallet.getBalance());
                walletHistory.setTradeNo(order.getOrderNumber()+"_"+op.getId());
                walletHistory.setInOut(Constant.WalletHistoryType_In);
                walletHistory.setTradeType(Constant.WalletHistoryTradeType_Share);
                walletHistory.setTradeDesc("[分享佣金]-"+user.getNickname()+"购买产品"+op.getTitle());
                walletHistory.insert();
            }

        }

        //检查代理进货金额是否满足升级代理等级条件
        AgentLevel agentLevel = agentLevelMapper.selectById(user.getAgentLevel());
        //Double money = baseMapper.queryFinishedOrderMoneyByUserId(userId);
        Double money = order.getTotalPrice().doubleValue()+order.getDiscount().doubleValue();
        log.info("money:"+money);
        List<AgentLevel> agentLevelList = agentLevelMapper.selectList(null);
        if (ObjectUtil.isNull(agentLevelList)) {
            log.info("agentLevelList为空");
            return true;
        }
        for(int i=0;i<agentLevelList.size();i++){
            if(agentLevelList.get(i).getLevelInt()<=agentLevel.getLevelInt())continue;//升级的代理等级不能低于当前等级
            if(agentLevelList.get(i).getId()==user.getAgentLevel())continue;//当前等级
            if(money>=agentLevelList.get(i).getMinStock().doubleValue()){
                //达到该等级最低进货额，自动升级为该等级
                user.setAgentLevel(agentLevelList.get(i).getId());
            }
        }
        user.updateById();
        return true;
    }

    @Override
    public Map<String,Object> queryOrderDiscount(OrderDTO orderDto) {
        Map<String,Object> map = new HashMap<>();
        List<OrderProductDTO> orderProductDTOList = orderDto.getOrderProductDTOList();

        double total = 0;
        //计算总价格
        for(int i=0;i<orderProductDTOList.size();i++){
            total+=(orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue());
        }


        UserVO userVO = userMapper.queryInfo(orderDto.getUserId());
        //查询出所有代理等级
        List<AgentLevel> agentLevelList = agentLevelMapper.selectList(null);
        for(int i=0;i<agentLevelList.size();i++){
            //如果代理等级低于当前用户代理等级，不操作
            log.info("1111---"+agentLevelList.get(i).getLevelInt());
            log.info("2222---"+userVO.getAgentLevel());
            if(agentLevelList.get(i).getLevelInt()<userVO.getAgentLevel())continue;
            //总价小于代理最低进货价，不自动升级
            if(total<agentLevelList.get(i).getMinStock().doubleValue()) continue;
            userVO.setAgentLevel(agentLevelList.get(i).getId());//自动升级，方便下面技术代理价格
        }
        BigDecimal discount = new BigDecimal("0");
        //检查用户代理等级对应的代理价格
        for(int i=0;i<orderProductDTOList.size();i++){
            //根据代理等级和产品id查询代理价格
            AgentLevelProductAward pa = agentLevelProductAwardMapper.queryInfo(userVO.getAgentLevel(),orderProductDTOList.get(i).getProductId());
            if (ObjectUtil.isNull(pa)) {
                //没有代理价格，不操作
                continue;
            }
            //优惠金额=原价-代理价格
            discount = discount.add(new BigDecimal((orderProductDTOList.get(i).getProductPrice().doubleValue())-(orderProductDTOList.get(i).getProductNum()*pa.getAgentPrice().doubleValue())));
            //discount+=(orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue())-(orderProductDTOList.get(i).getProductNum()*pa.getAgentPrice().doubleValue());
            //有代理价格，按代理价格支付
            orderProductDTOList.get(i).setProductPrice(pa.getAgentPrice());
            log.info("money1:"+discount);

        }
        // 计算总价格
        //BigDecimal totalPriceNew = orderUtil.addOrderProduct(orderProductDTOList, order.getId(), order.getUserId());
       // BigDecimal payMoney = totalPriceNew.add(orderDto.getFreight());
        log.info("money:"+discount);
        map.put("discount",discount.setScale(2, BigDecimal.ROUND_FLOOR).toString());
        map.put("data",orderProductDTOList);
        return map;
    }


    public Boolean changeOrderState(Integer orderId, Integer userId, Integer state) {
        Order order = baseMapper.selectById(orderId);
        if (ObjectUtil.isNull(order)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }
        if(userId==null || userId==0)throw new MyException(CodeMsg.USER_NOT_EXISTED);
        order.setOrderState(state);
        order.updateById();
        return true;
    }
}