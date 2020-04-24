package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.OrderListVO;
import com.zhancheng.core.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 订单
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 查询订单列表
     *
     * @param page
     * @param orderQueryDto
     * @return page
     */
    IPage<OrderListVO> queryPage(Page page, @Param("query") OrderQueryDTO orderQueryDto);

    /**
     * 查询订单详情
     *
     * @param id
     * @return order vo
     */
    OrderVO queryInfo(Integer id);

    OrderVO selectOneByOrderNumber(String order_number);

    Double queryFinishedOrderMoneyByUserId(Integer userId);
}
