package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.OrderLogistic;
import com.zhancheng.core.entity.OrderProduct;
import com.zhancheng.core.vo.OrderLogisticVO;

/**
 * 订单商品
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
public interface OrderLogisticService extends IService<OrderLogistic> {

    OrderLogisticVO queryInfo(Integer id);
}

