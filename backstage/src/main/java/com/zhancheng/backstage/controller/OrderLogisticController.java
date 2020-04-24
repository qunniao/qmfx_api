package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.OrderLogisticService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.OrderLogisticQueryDTO;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.entity.OrderLogistic;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.vo.OrderListVO;
import com.zhancheng.core.vo.OrderLogisticVO;
import com.zhancheng.core.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


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
public class OrderLogisticController {

    @Resource
    private OrderLogisticService orderLogisticService;


    //@Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询列表")
    @ApiImplicitParam(name = "orderId", value = "订单id")
    @GetMapping("/list")
    public R<List<OrderLogisticVO>> queryList(Integer orderId) {

        return R.ok(orderLogisticService.queryList(orderId));
    }

    //@Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<OrderLogisticVO> queryInfo(@PathVariable("id") Integer id) {

        return R.ok(orderLogisticService.queryInfo(id));
    }

    //@Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加物流")
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
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody OrderLogisticQueryDTO item) {

        return R.ok(orderLogisticService.insert(item));
    }

    //@Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改物流")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "商品id"),
            @ApiImplicitParam(name = "typeId", value = "商品类型id"),
            @ApiImplicitParam(name = "productNumber", value = "商品货号"),
            @ApiImplicitParam(name = "title", value = "商品名称"),
            @ApiImplicitParam(name = "cover", value = "商品主图"),
            @ApiImplicitParam(name = "video", value = "商品介绍视频"),
            @ApiImplicitParam(name = "productIntro", value = "商品简介"),
            @ApiImplicitParam(name = "marketPrice", value = "市场价"),
            @ApiImplicitParam(name = "price", value = "售价"),
            @ApiImplicitParam(name = "stockPrice", value = "进货价"),
            @ApiImplicitParam(name = "sales", value = "基础销量：系统虚拟的销量基数"),
            @ApiImplicitParam(name = "flowSales", value = "流水销量，提交订单后增加"),
            @ApiImplicitParam(name = "realSales", value = "实际销量，确认收货后增加，退换货减少"),
            @ApiImplicitParam(name = "totalStock", value = "库存数量，发货后减少"),
            @ApiImplicitParam(name = "detailMobile", value = "手机端详情"),
            @ApiImplicitParam(name = "isRecommend", value = "推荐 1.是0.否"),
            @ApiImplicitParam(name = "retailFreightId", value = "零售运费模板id")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody OrderLogistic item){

        return R.ok(orderLogisticService.update(item));
    }

    //@Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> pids){

        return R.ok(orderLogisticService.delete(pids));
    }
}
