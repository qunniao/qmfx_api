package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.OrderLogisticQueryDTO;
import com.zhancheng.core.entity.OrderLogistic;
import com.zhancheng.core.entity.OrderProduct;
import com.zhancheng.core.vo.OrderLogisticVO;
import com.zhancheng.core.vo.OrderProductVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单商品
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface OrderLogisticMapper extends BaseMapper<OrderLogistic> {

    /**
     * 根据订单id查询订单商品物流
     *
     * @param orderId
     * @return order product
     */
    List<OrderLogisticVO> queryList(Integer orderId);

    OrderLogisticVO queryInfo(Integer id);

    List<OrderLogisticVO> queryList2(Integer id);
}
