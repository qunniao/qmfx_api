package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.OrderDTO;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.dto.OrderRefundDTO;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.entity.OrderRefund;
import com.zhancheng.core.vo.OrderListVO;
import com.zhancheng.core.vo.OrderVO;

import java.util.Map;

/**
 * 订单退换货
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
public interface OrderRefundService extends IService<OrderRefund> {


    /**
     * 提交订单
     * @param modal 订单退换货信息
     * @return
     */
    boolean insert(OrderRefundDTO modal);


}

