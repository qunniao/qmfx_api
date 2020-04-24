package com.zhancheng.applet.service.impl;

import com.zhancheng.applet.service.OrderProductService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.entity.OrderProduct;

/**
 * 订单商品
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements OrderProductService {

}