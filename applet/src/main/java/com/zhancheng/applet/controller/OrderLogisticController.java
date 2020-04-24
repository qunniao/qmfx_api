package com.zhancheng.applet.controller;

import com.zhancheng.applet.service.OrderLogisticService;
import com.zhancheng.applet.service.OrderRefundService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.OrderRefundDTO;
import com.zhancheng.core.vo.OrderLogisticVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 订单物流跟踪
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "订单物流跟踪")
@RestController
@RequestMapping("/orderLogistic")
@Verify(role = UserIdentity.ORDINARY)
public class OrderLogisticController {

    @Resource
    private OrderLogisticService orderLogisticService;


    @ApiOperation(value = "查询订单物流详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<OrderLogisticVO> queryInfo(@PathVariable("id") Integer id) {

        return R.ok(orderLogisticService.queryInfo(id));
    }





}
