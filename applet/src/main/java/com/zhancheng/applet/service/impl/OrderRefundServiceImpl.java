package com.zhancheng.applet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.OrderProductService;
import com.zhancheng.applet.service.OrderRefundService;
import com.zhancheng.applet.service.OrderService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.Constant;
import com.zhancheng.core.dao.*;
import com.zhancheng.core.dto.OrderDTO;
import com.zhancheng.core.dto.OrderProductDTO;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.dto.OrderRefundDTO;
import com.zhancheng.core.entity.*;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.util.NumberUtil;
import com.zhancheng.core.util.OrderUtil;
import com.zhancheng.core.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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


}