package com.zhancheng.backstage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.OrderRefundService;
import com.zhancheng.backstage.service.WxPayService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.Constant;
import com.zhancheng.core.dao.OrderMapper;
import com.zhancheng.core.dao.OrderRefundMapper;
import com.zhancheng.core.dto.OrderRefundDTO;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.entity.OrderRefund;
import com.zhancheng.core.vo.OrderRefundVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单退换货
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class OrderRefundServiceImpl extends ServiceImpl<OrderRefundMapper, OrderRefund> implements OrderRefundService {
    private static Logger log = LoggerFactory.getLogger(OrderRefundServiceImpl.class);
    @Resource
    private OrderRefundMapper orderRefundMapper;
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private WxPayService wxPayService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(OrderRefundDTO orderRefundDTO) {
        Order order = orderMapper.selectById(orderRefundDTO.getOrderId());

        // 添加订单退换货信息
        OrderRefund item = new OrderRefund();
        BeanUtil.copyProperties(orderRefundDTO, item);
        item.setRefundState(Constant.RefundStateWait);
        item.setOrderState(order.getOrderState());
        item.insert();

        order.setOrderState(Constant.OrderStateRefund);//订单退款中
        order.updateById();
        return true;
    }

    @Override
    public IPage<OrderRefundVO> queryPage(PageDto<Order> pageDto, OrderRefundDTO orderRefundDTO) {
        IPage<OrderRefundVO> list = baseMapper.queryPage(pageDto.getPage(),orderRefundDTO);
        return list;
    }

    @Override
    public Boolean update(OrderRefundDTO modal) {
        OrderRefund item = new OrderRefund();
        BeanUtil.copyProperties(modal, item);
        item.updateById();
        if(item.getRefundState()!=Constant.RefundStateService){
            //如果不是同意退款就没必要往下走
            return true;
        }

        Order order = orderMapper.selectById(item.getOrderId());
        if(order.getPayWay()==1){
            //微信支付方式
            boolean result = wxRefund(order,item);
            return result;
        }
       return false;
    }

    private boolean wxRefund(Order order, OrderRefund item) {
        //微信退款
        Map<String, String> data = new HashMap<String, String>();
        System.err.println("进入微信退款申请");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式
        String hehe = dateFormat.format(now);

        String out_refund_no = item.getId()+"";
        String transaction_id = order.getPayNumber();

        String total_fee = order.getPayMoney().multiply(new BigDecimal(100)).intValue() + "";
        String refund_fee = item.getRefundMoney().multiply(new BigDecimal(100)).intValue() + "";
        data.put("out_refund_no", out_refund_no);
        data.put("transaction_id", transaction_id);
        data.put("total_fee", total_fee);
        data.put("refund_fee",refund_fee);

        String result = null;
        try {
            result = wxPayService.refund(data);
            log.info("result:"+result);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //判断是否退款成功
        if (result.equals("SUCCESS")) {
            item.setRefundState(Constant.RefundStateService);
            item.updateById();
            return  true;
        }else{
            item.setRefundState(Constant.RefundStateFail);
            item.updateById();
        }
        return false;
    }


}