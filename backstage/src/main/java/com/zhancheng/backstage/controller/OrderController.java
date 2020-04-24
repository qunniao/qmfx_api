package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.OrderService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.dto.ShipmentsDTO;
import com.zhancheng.core.entity.OrderLogistic;
import com.zhancheng.core.entity.Question;
import com.zhancheng.core.vo.OrderListVO;
import com.zhancheng.core.vo.OrderVO;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.Order;

import javax.annotation.Resource;


/**
 * 订单
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "订单")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询订单列表")
    @GetMapping("/page")
    public R<IPage<OrderListVO>> queryPage(PageDto<Order> pageDto, OrderQueryDTO orderQueryDto) {

        return R.ok(orderService.queryPage(pageDto, orderQueryDto));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询订单详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<OrderVO> queryInfo(@PathVariable("id") Integer id) {

        return R.ok(orderService.queryInfo(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id"),
    })
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {

        return R.ok(orderService.removeById(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "发货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id"),
            @ApiImplicitParam(name = "orderProductId", value = "订单产品id拼接字符串"),
            @ApiImplicitParam(name = "expressCompany", value = "快递公司"),
            @ApiImplicitParam(name = "expressNumber", value = "快递公司编号"),
            @ApiImplicitParam(name = "logisticCode", value = "快递单号"),
            @ApiImplicitParam(name = "sendType", value = "发货类型：0统一发货，1分别发货"),
            @ApiImplicitParam(name = "contactName", value = "联系人"),
            @ApiImplicitParam(name = "contactPhone", value = "联系电话"),
            @ApiImplicitParam(name = "contactAddress", value = "收货地址")
    })
    @PostMapping("/shipments/{id}")
    public R<Boolean> shipments(@PathVariable Integer id) {

        return R.ok(orderService.shipments(id));
    }

}
