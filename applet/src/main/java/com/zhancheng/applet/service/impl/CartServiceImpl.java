package com.zhancheng.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.CartService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.dto.OrderCartDTO;
import com.zhancheng.core.dto.OrderProductDTO;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.util.NumberUtil;
import com.zhancheng.core.util.OrderUtil;
import com.zhancheng.core.vo.CartConfirmListVO;
import com.zhancheng.core.vo.CartListVO;
import com.zhancheng.core.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    private static Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
    @Resource
    private ProductMapper productMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private OrderUtil orderUtil;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Resource
    private AgentLevelProductAwardMapper agentLevelProductAwardMapper;

    @Resource
    private FreightMapper freightMapper;


    @Override
    public IPage<CartListVO> queryPage(PageDto<Cart> pageDto, Integer userId) {
        return baseMapper.queryPage(pageDto.getPage(), userId);
    }

    @Override
    public String submitCart(OrderCartDTO orderCartDto) {

        Order order = new Order();
        BeanUtil.copyProperties(orderCartDto, order);

        String orderNumber = NumberUtil.generateOrderNumber();
        order.setFreight(orderCartDto.getFreight());
        order.setOrderNumber(orderNumber);
        order.insert();

        List<Integer> cIdList = orderCartDto.getCidList();
        if (ObjectUtil.isNull(cIdList)) {
            throw new MyException(CodeMsg.CART_NOT_EXIST);
        }

        List<OrderProductDTO> orderProductDTOList = new ArrayList<>();

        List<Cart> carts = cartMapper.selectBatchIds(cIdList);

        for (Cart cart : carts) {

            OrderProductDTO orderProductDto = new OrderProductDTO();
            BeanUtil.copyProperties(cart, orderProductDto);
            orderProductDTOList.add(orderProductDto);
            log.info("cart id:"+cart.getProductId()+" orderProductDto id:"+orderProductDto.getProductId());
        }

        // 删除代理购物车
        cartMapper.deleteBatchIds(cIdList);

        /*
        // 添加产品订单并计算总价格
        BigDecimal totalPrice = orderUtil.addOrderProduct(orderProductDTOList, order.getId(), order.getUserId());

        BigDecimal payMoney = totalPrice.add(orderCartDto.getFreight());

        // 修改订单总价格
        order.setTotalPrice(totalPrice).setPayMoney(payMoney);
        order.updateById();

         */
        double total = 0;
        //计算总价格
        for(int i=0;i<orderProductDTOList.size();i++){
            total+=(orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue());
        }


        UserVO userVO = userMapper.queryInfo(order.getUserId());
        AgentLevel agentLevel = agentLevelMapper.selectById(userVO.getAgentLevel());
        //查询出所有代理等级
        List<AgentLevel> agentLevelList = agentLevelMapper.selectList(null);
        for(int i=0;i<agentLevelList.size();i++){
            if(agentLevelList.get(i).getLevelInt()<=agentLevel.getLevelInt())continue;//代理列表等级低于现有等级，不操作
            //总价小于代理最低进货价，不自动升级
            if(total<agentLevelList.get(i).getMinStock().doubleValue()) continue;
            userVO.setAgentLevel(agentLevelList.get(i).getId());//自动升级，方便下面技术代理价格
        }
        double discount = 0;
        //检查用户代理等级对应的代理价格
        for(int i=0;i<orderProductDTOList.size();i++){
            log.info("检查用户代理等级对应的代理价格："+userVO.getAgentLevel()+" userid:"+order.getUserId());
            //根据代理等级和产品id查询代理价格
            AgentLevelProductAward pa = agentLevelProductAwardMapper.queryInfo(userVO.getAgentLevel(),orderProductDTOList.get(i).getProductId());
            if (ObjectUtil.isNull(pa)) {
                //没有代理价格，不操作
                continue;
            }
            //优惠金额=原价-代理价格
            discount+=(orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue())-(orderProductDTOList.get(i).getProductNum()*pa.getAgentPrice().doubleValue());
            //有代理价格，按代理价格支付
            log.info("原价："+orderProductDTOList.get(i).getProductPrice());
            orderProductDTOList.get(i).setProductPrice(pa.getAgentPrice());
            log.info("代理价："+orderProductDTOList.get(i).getProductPrice());

        }
        // 添加产品订单并计算总价格
        BigDecimal totalPriceNew = orderUtil.addOrderProduct(orderProductDTOList, order.getId(), order.getUserId());
        BigDecimal payMoney = totalPriceNew.add(order.getFreight());
        log.info("totalPriceNew:"+totalPriceNew+" payMoney:"+payMoney+" freight:"+order.getFreight());
        // 修改订单总价格(加上运费后)
        order.setTotalPrice(totalPriceNew).setPayMoney(payMoney).setFreight(order.getFreight());
        order.setDiscount(new BigDecimal(discount));
        order.setFreight(order.getFreight());
        order.updateById();

        return order.getOrderNumber();
    }

    @Override
    public Boolean insertOrUpdate(Cart cart) {

        // 购买的产品数量
        Integer productNum = cart.getProductNum();
        //商品id
        Integer productId = cart.getProductId();

        Cart cartInfo = baseMapper.selectOne(new QueryWrapper<Cart>()
                .eq("product_id", productId).eq("user_id", cart.getUserId()));

        //查询商品
        Product product = productMapper.selectById(productId);
        if (ObjectUtil.isNull(product)) {
            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }

        //商品库存
        Integer totalStock = product.getTotalStock();

        if (ObjectUtil.isNotNull(cartInfo)) {

            // 增加后的购物商品数量
            int num = productNum + cartInfo.getProductNum();

            if (num > totalStock) {
                throw new MyException(CodeMsg.GOODS_NUM_ERROR);
            }
            cartInfo.setProductNum(num);
            cartInfo.updateById();
        } else {

            if (productNum > totalStock) {
                throw new MyException(CodeMsg.GOODS_NUM_ERROR);
            }
            cart.insert();
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean changeProductNum(Integer cid, Integer num) {

        Cart cart = baseMapper.selectById(cid);
        if (ObjectUtil.isNull(cart)) {
            throw new MyException(CodeMsg.CART_NOT_EXIST);
        }

        //查询商品
        Product product = productMapper.selectById(cart.getProductId());
        if (ObjectUtil.isNull(product)) {
            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }

        if (num > product.getTotalStock()) {
            throw new MyException(CodeMsg.GOODS_NUM_ERROR);
        }

        cart.setProductNum(num);

        return cart.updateById();
    }

    @Override
    public Map<String, Object> queryInfo(List<Integer> cIds) {
        Map<String,Object> map = new HashMap<>();
        //购物车列表
        List<CartListVO> cartList = baseMapper.queryInfo(cIds);
        //查询优惠金额
        double total = 0;
        int userId = 0;
        //计算总价格
        for(int i=0;i<cartList.size();i++){
            total+=(cartList.get(i).getProductNum()*cartList.get(i).getProductPrice().doubleValue());
            if(i==0){
                userId = cartList.get(i).getUserId();
            }
        }


        UserVO userVO = userMapper.queryInfo(userId);
        //查询出所有代理等级
        List<AgentLevel> agentLevelList = agentLevelMapper.selectList(null);
        for(int i=0;i<agentLevelList.size();i++){
            //如果代理等级低于当前用户代理等级，不操作
            if(agentLevelList.get(i).getLevelInt()<userVO.getAgentLevel())continue;
            //总价小于代理最低进货价，不自动升级
            if(total<agentLevelList.get(i).getMinStock().doubleValue()) continue;
            userVO.setAgentLevel(agentLevelList.get(i).getId());//自动升级，方便下面技术代理价格
        }
        BigDecimal discount = new BigDecimal("0");
        //检查用户代理等级对应的代理价格
        for(int i=0;i<cartList.size();i++){
            //根据代理等级和产品id查询代理价格
            AgentLevelProductAward pa = agentLevelProductAwardMapper.queryInfo(userVO.getAgentLevel(),cartList.get(i).getProductId());
            if (ObjectUtil.isNull(pa)) {
                //没有代理价格，不操作
                continue;
            }
            //优惠金额=原价-代理价格
            discount = discount.add(new BigDecimal((cartList.get(i).getProductNum()*cartList.get(i).getProductPrice().doubleValue())-(cartList.get(i).getProductNum()*pa.getAgentPrice().doubleValue())));
            //discount+=(orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue())-(orderProductDTOList.get(i).getProductNum()*pa.getAgentPrice().doubleValue());
            //有代理价格，按代理价格支付
            cartList.get(i).setProductPrice(pa.getAgentPrice());

        }

        //计算运费
        total = 0;
        double f = 0;
        Freight freight =null;
        List<FreightRegion> freightRegionList = null;
        FreightRegion freightRegion = null;
        //检查每个产品对应的运费模板
        for(int i=0;i<cartList.size();i++){
            //重新计算订单总价
            total+=(cartList.get(i).getProductNum()*cartList.get(i).getProductPrice().doubleValue());

            freight = freightMapper.queryInfo(cartList.get(i).getRetailFreightId());
            freightRegionList = freight.getFreightRegionList();
            for(int k = 0;k<freightRegionList.size();k++){
                freightRegion = freightRegionList.get(k);
                if (freightRegion.getValuationWay()==2){
                    //按件数
                    if (freightRegion.getType()==1){
                        //全部地区运费
                        if (freightRegion.getFirstNumber() > cartList.get(i).getProductNum())continue;//产品数量小于运费的首件件数，没有运费
                        f += freightRegion.getFirstPrice().doubleValue(); //首件运费
                        f += ((cartList.get(i).getProductNum() - freightRegion.getFirstNumber()) / freightRegion.getRenewNumber()) * freightRegion.getRenewPrice().doubleValue();

                    }else{
                        //部分地区运费
                    }
                }else{
                    //按重量
                }
            }
        }

        map.put("data",cartList);
        map.put("discount",discount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        map.put("freight",new BigDecimal(f).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        map.put("orderPrice",new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        map.put("totalPrice",new BigDecimal(total+f).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        log.info("total:"+total+" 运费f:"+f+" discount:"+discount+" totalPrice:"+map.get("totalPrice"));
        return map;
    }

    @Override
    public Map<String, Object> queryProductOrderPrice(Integer userId,Integer productId,Integer productNum) {
        Map<String,Object> map = new HashMap<>();
        //产品信息
        Product product= productMapper.selectById(productId);
        List<CartListVO> cartList = new ArrayList<>();
        CartListVO cartListVO = new CartListVO();
        cartListVO.setProductPrice(product.getPrice());
        cartListVO.setProductId(productId);
        cartListVO.setCover(product.getCover());
        cartListVO.setProductName(product.getTitle());
        cartListVO.setProductNum(productNum);
        cartListVO.setUserId(userId);
        cartListVO.setRetailFreightId(product.getRetailFreightId());
        cartList.add(cartListVO);
        //查询优惠金额
        double total = 0;

        //计算总价格
        total+=(productNum*product.getPrice().doubleValue());

        UserVO userVO = userMapper.queryInfo(userId);
        //查询出所有代理等级
        List<AgentLevel> agentLevelList = agentLevelMapper.selectList(null);
        for(int i=0;i<agentLevelList.size();i++){
            //如果代理等级低于当前用户代理等级，不操作
            if(agentLevelList.get(i).getLevelInt()<userVO.getAgentLevel())continue;
            //总价小于代理最低进货价，不自动升级
            if(total<agentLevelList.get(i).getMinStock().doubleValue()) continue;
            userVO.setAgentLevel(agentLevelList.get(i).getId());//自动升级，方便下面技术代理价格
        }
        BigDecimal discount = new BigDecimal("0");
        //检查用户代理等级对应的代理价格
        for(int i=0;i<cartList.size();i++){
            //根据代理等级和产品id查询代理价格
            AgentLevelProductAward pa = agentLevelProductAwardMapper.queryInfo(userVO.getAgentLevel(),cartList.get(i).getProductId());
            if (ObjectUtil.isNull(pa)) {
                //没有代理价格，不操作
                continue;
            }
            //优惠金额=原价-代理价格
            discount = discount.add(new BigDecimal((cartList.get(i).getProductNum()*cartList.get(i).getProductPrice().doubleValue())-(cartList.get(i).getProductNum()*pa.getAgentPrice().doubleValue())));
            //discount+=(orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue())-(orderProductDTOList.get(i).getProductNum()*pa.getAgentPrice().doubleValue());
            //有代理价格，按代理价格支付
            cartList.get(i).setProductPrice(pa.getAgentPrice());

        }

        //计算运费
        total = 0;
        double f = 0;
        Freight freight =null;
        List<FreightRegion> freightRegionList = null;
        FreightRegion freightRegion = null;
        //检查每个产品对应的运费模板
        for(int i=0;i<cartList.size();i++){
            //重新计算订单总价
            total+=(cartList.get(i).getProductNum()*cartList.get(i).getProductPrice().doubleValue());

            freight = freightMapper.queryInfo(cartList.get(i).getRetailFreightId());
            freightRegionList = freight.getFreightRegionList();
            for(int k = 0;k<freightRegionList.size();k++){
                freightRegion = freightRegionList.get(k);
                if (freightRegion.getValuationWay()==2){
                    //按件数
                    if (freightRegion.getType()==1){
                        //全部地区运费
                        if (freightRegion.getFirstNumber() > cartList.get(i).getProductNum())continue;//产品数量小于运费的首件件数，没有运费
                        f += freightRegion.getFirstPrice().doubleValue(); //首件运费
                        f += ((cartList.get(i).getProductNum() - freightRegion.getFirstNumber()) / freightRegion.getRenewNumber()) * freightRegion.getRenewPrice().doubleValue();

                    }else{
                        //部分地区运费
                    }
                }else{
                    //按重量
                }
            }
        }

        map.put("data",cartList);
        map.put("discount",discount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        map.put("freight",new BigDecimal(f).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        map.put("orderPrice",new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        map.put("totalPrice",new BigDecimal(total+f).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        log.info("total:"+total+" 运费f:"+f+" discount:"+discount+" totalPrice:"+map.get("totalPrice"));
        return map;
    }

    @Override
    public Map<String, Object> queryCartDiscount(OrderCartDTO orderCartDto) {
        Map<String,Object> map = new HashMap<>();
        List<Integer> cIdList = orderCartDto.getCidList();
        if (ObjectUtil.isNull(cIdList)) {
            throw new MyException(CodeMsg.CART_NOT_EXIST);
        }

        List<OrderProductDTO> orderProductDTOList = new ArrayList<>();

        List<Cart> carts = cartMapper.selectBatchIds(cIdList);

        for (Cart cart : carts) {

            OrderProductDTO orderProductDto = new OrderProductDTO();
            BeanUtil.copyProperties(cart, orderProductDto);
            orderProductDTOList.add(orderProductDto);
            log.info("cart id:"+cart.getProductId()+" orderProductDto id:"+orderProductDto.getProductId());
        }


        double total = 0;
        //计算总价格
        for(int i=0;i<orderProductDTOList.size();i++){
            total+=(orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue());
        }


        UserVO userVO = userMapper.queryInfo(orderCartDto.getUserId());
        //查询出所有代理等级
        List<AgentLevel> agentLevelList = agentLevelMapper.selectList(null);
        for(int i=0;i<agentLevelList.size();i++){
            //如果代理等级低于当前用户代理等级，不操作
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
            discount = discount.add(new BigDecimal((orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue())-(orderProductDTOList.get(i).getProductNum()*pa.getAgentPrice().doubleValue())));
            //discount+=(orderProductDTOList.get(i).getProductNum()*orderProductDTOList.get(i).getProductPrice().doubleValue())-(orderProductDTOList.get(i).getProductNum()*pa.getAgentPrice().doubleValue());
            //有代理价格，按代理价格支付
            orderProductDTOList.get(i).setProductPrice(pa.getAgentPrice());

        }

        map.put("discount",discount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        map.put("data",orderProductDTOList);
        return map;
    }
}