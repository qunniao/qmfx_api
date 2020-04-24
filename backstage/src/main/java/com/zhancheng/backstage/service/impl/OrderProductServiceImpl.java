package com.zhancheng.backstage.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.OrderProductService;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.entity.OrderProduct;
import org.springframework.stereotype.Service;

/**
 * 订单商品
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements OrderProductService {

}