package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.OrderRefundService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.dto.OrderRefundDTO;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.vo.OrderListVO;
import com.zhancheng.core.vo.OrderRefundVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 订单退换货
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "订单退换货")
@RestController
@RequestMapping("/orderRefund")
public class OrderRefundController {

    @Resource
    private OrderRefundService orderRefundService;

    //@Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询待退款列表")
    @GetMapping("/page")
    public R<IPage<OrderRefundVO>> queryPage(PageDto<Order> pageDto, OrderRefundDTO orderRefundDTO) {

        return R.ok(orderRefundService.queryPage(pageDto, orderRefundDTO));
    }


    @ApiOperation(value = "提交订单退换货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id"),
            @ApiImplicitParam(name = "orderProductId", value = "订单产品id"),
            @ApiImplicitParam(name = "refundType", value = "退款类型：1只退款无需退货，2退货退款，3换货"),
            @ApiImplicitParam(name = "productState", value = "货物状态：1未收到货，2已收到货"),
            @ApiImplicitParam(name = "refundWhy", value = "退款原因"),
            @ApiImplicitParam(name = "refundMoney", value = "退款金额"),
            @ApiImplicitParam(name = "note", value = "退款说明"),
            @ApiImplicitParam(name = "pic1", value = "凭证图片1"),
            @ApiImplicitParam(name = "pic2", value = "凭证图片2"),
            @ApiImplicitParam(name = "pic3", value = "凭证图片3"),
            @ApiImplicitParam(name = "refundWay", value = "退货方式：1自行寄回，2上门取件"),
            @ApiImplicitParam(name = "refundDeliveryNumber", value = "退货的快递单号"),
            @ApiImplicitParam(name = "refundDeliveryCompany", value = "退货的快递公司名称"),
            @ApiImplicitParam(name = "refundState", value = "退货状态")
    })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody OrderRefundDTO modal) {

        return R.ok(orderRefundService.insert(modal));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改退款申请数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "orderId", value = "订单id"),
            @ApiImplicitParam(name = "orderProductId", value = "订单产品id"),
            @ApiImplicitParam(name = "refundType", value = "退款类型：1只退款无需退货，2退货退款，3换货"),
            @ApiImplicitParam(name = "productState", value = "货物状态：1未收到货，2已收到货"),
            @ApiImplicitParam(name = "refundWhy", value = "退款原因"),
            @ApiImplicitParam(name = "refundMoney", value = "退款金额"),
            @ApiImplicitParam(name = "note", value = "退款说明"),
            @ApiImplicitParam(name = "pic1", value = "凭证图片1"),
            @ApiImplicitParam(name = "pic2", value = "凭证图片2"),
            @ApiImplicitParam(name = "pic3", value = "凭证图片3"),
            @ApiImplicitParam(name = "refundWay", value = "退货方式：1自行寄回，2上门取件"),
            @ApiImplicitParam(name = "refundDeliveryNumber", value = "退货的快递单号"),
            @ApiImplicitParam(name = "refundDeliveryCompany", value = "退货的快递公司名称"),
            @ApiImplicitParam(name = "refundState", value = "退货状态")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody OrderRefundDTO modal){

        return R.ok(orderRefundService.update(modal));
    }
}
