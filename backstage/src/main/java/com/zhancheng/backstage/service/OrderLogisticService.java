package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.OrderLogisticQueryDTO;
import com.zhancheng.core.entity.OrderLogistic;
import com.zhancheng.core.vo.OrderLogisticVO;

import java.util.List;

/**
 * 订单商品
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
public interface OrderLogisticService extends IService<OrderLogistic> {

    OrderLogisticVO queryInfo(Integer id);

    List<OrderLogisticVO> queryList(Integer orderId);

    Boolean insert(OrderLogisticQueryDTO item);

    Boolean update(OrderLogistic item);

    Boolean delete(List<Integer> pids);
}

