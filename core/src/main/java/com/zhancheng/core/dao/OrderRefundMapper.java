package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.dto.OrderRefundDTO;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.entity.OrderRefund;
import com.zhancheng.core.vo.OrderListVO;
import com.zhancheng.core.vo.OrderRefundVO;
import com.zhancheng.core.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 订单退换货
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface OrderRefundMapper extends BaseMapper<OrderRefund> {


    IPage<OrderRefundVO> queryPage(Page<Order> page, OrderRefundDTO orderRefundDTO);
}
