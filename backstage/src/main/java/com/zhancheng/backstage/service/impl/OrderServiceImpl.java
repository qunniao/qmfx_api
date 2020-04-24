package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.OrderService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.Constant;
import com.zhancheng.core.dao.OrderLogisticMapper;
import com.zhancheng.core.dao.OrderMapper;
import com.zhancheng.core.dao.OrderProductMapper;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.entity.OrderLogistic;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.vo.OrderListVO;
import com.zhancheng.core.vo.OrderLogisticVO;
import com.zhancheng.core.vo.OrderProductVO;
import com.zhancheng.core.vo.OrderVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderProductMapper orderProductMapper;
    @Resource
    private OrderLogisticMapper orderLogisticMapper;

    @Override
    public IPage<OrderListVO> queryPage(PageDto<Order> pageDto, OrderQueryDTO orderQueryDto) {

        IPage<OrderListVO> orderListPage = baseMapper.queryPage(pageDto.getPage(), orderQueryDto);

        List<OrderListVO> records = orderListPage.getRecords();

        if (ObjectUtil.isNotEmpty(records)) {
            for (OrderListVO orderListVO : records) {
                List<OrderProductVO> orderProductList = orderProductMapper.queryList(orderListVO.getId());
                orderListVO.setOrderProductList(orderProductList);
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

        if(orderVo.getOrderState()> Constant.OrderStateWaitSend){
            //已发货的订单获取物流信息，可能有多个物流
            List<OrderLogisticVO> orderLogisticList = orderLogisticMapper.queryList(id);

            for(int i=0;i<orderLogisticList.size();i++){
                String[] strs = orderLogisticList.get(i).getOrderProductId().split(",");
                List<OrderProductVO> opList = new ArrayList<>();
                for(int j=0;j<strs.length;j++){
                    if(strs[j].equals(""))continue;
                    opList.add(orderProductMapper.queryInfo(new Integer(strs[j])));
                }
                orderLogisticList.get(i).setOrderProductList(opList);
            }
            orderVo.setOrderLogisticVOList(orderLogisticList);
        }
        return orderVo;
    }

    @Override
    public Boolean shipments(Integer id) {

        Order order = baseMapper.selectById(id);

        if (ObjectUtil.isNull(order)) {
            throw new MyException(CodeMsg.ORDER_IS_NULL);
        }
/*
        if (ObjectUtil.isNotNull(order.getDeliveryWay()) && order.getDeliveryWay() == 1) {
            order.setExpressCompany(shipmentsDto.getExpressCompany()).setExpressNumber(shipmentsDto.getExpressNumber());
        }
*/
        order.setSendTime(new Date()).setOrderState(OrderStateEnum.AWAIT_RECEIVING.getState());
        order.updateById();

        return true;
    }
}