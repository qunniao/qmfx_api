package com.zhancheng.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.OrderService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.OrderDTO;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.enums.OrderStateEnum;
import com.zhancheng.core.vo.OrderListVO;
import com.zhancheng.core.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


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
@Verify(role = UserIdentity.ORDINARY)
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "分页查询订单列表")
    @GetMapping("/page")
    public R<IPage<OrderListVO>> queryPage(PageDto<Order> pageDto, OrderQueryDTO orderQueryDto) {

        return R.ok(orderService.queryPage(pageDto, orderQueryDto));
    }

    @ApiOperation(value = "查询订单详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<OrderVO> queryInfo(@PathVariable("id") Integer id) {

        return R.ok(orderService.queryInfo(id));
    }


    @ApiOperation(value = "提交订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(name = "contactName", value = "联系人",required = true),
            @ApiImplicitParam(name = "contactPhone", value = "联系电话", required = true),
            @ApiImplicitParam(name = "contactAddress", value = "收货地址", required = true),
            @ApiImplicitParam(name = "freight", value = "运费:包邮就传0",required = true),
            @ApiImplicitParam(name = "remark", value = "备注留言"),
            @ApiImplicitParam(name = "deliveryWay", value = "配送方式1.快递2.无需物流3.自提；默认为1"),
            @ApiImplicitParam(name = "orderProductList", value = "订单商品集合",required = true),
    })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody OrderDTO orderDto) {

        return R.ok(orderService.insert(orderDto));
    }



    @ApiOperation(value = "取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id"),
    })
    @GetMapping("/cancel/{orderId}")
    public R<Boolean> cancel(@PathVariable("orderId") Integer orderId) {

        return R.ok(orderService.cancel(orderId));
    }

    @ApiOperation(value = "删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id"),
    })
    @GetMapping("/delete/{orderId}")
    public R<Boolean> delete(@PathVariable("orderId") Integer orderId) {

        return R.ok(orderService.removeById(orderId));
    }

    @ApiOperation(value = "将订单转入云库存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    })
    @PostMapping("/changeToCloudStore")
    public R<Boolean> changeToCloudStore(Integer orderId, Integer userId) {

        return R.ok(orderService.changeToCloudStore(orderId,userId));
    }

    @ApiOperation(value = "确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    })
    @PostMapping("/finishOrder")
    public R<Boolean> finishOrder(Integer orderId, Integer userId) {

        return R.ok(orderService.finishOrder(orderId,userId));
    }

    @ApiOperation(value = "查询订单优惠金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(name = "orderProductList", value = "订单商品集合",required = true),
    })
    @PostMapping("/queryOrderDiscount")
    public R<Map<String,Object>> queryOrderDiscount(@RequestBody OrderDTO orderDto) {

        return R.ok(orderService.queryOrderDiscount(orderDto));
    }
}
