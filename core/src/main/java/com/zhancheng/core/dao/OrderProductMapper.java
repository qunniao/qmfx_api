package com.zhancheng.core.dao;

import com.zhancheng.core.entity.OrderProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface OrderProductMapper extends BaseMapper<OrderProduct> {

    /**
     * 根据订单id查询订单商品
     *
     * @param orderId
     * @return order product
     */
    List<OrderProductVO> queryList(Integer orderId);

    OrderProductVO queryInfo(Integer id);
}
