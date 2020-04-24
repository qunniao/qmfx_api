package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.entity.OrderLogistic;
import com.zhancheng.core.vo.OrderListVO;
import com.zhancheng.core.vo.OrderVO;

/**
 * 订单
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
public interface OrderService extends IService<Order> {

    /**
     * 查询订单列表
     *
     * @param pageDto       分页信息
     * @param orderQueryDto 查询参数
     * @return page
     */
    IPage<OrderListVO> queryPage(PageDto<Order> pageDto, OrderQueryDTO orderQueryDto);

    /**
     * 查询订单详情
     *
     * @param id 订单id
     * @return order vo
     */
    OrderVO queryInfo(Integer id);

    /**
     * 查询订单详情
     *
     * @return order vo
     * @param id
     */
    Boolean shipments(Integer id);

}

