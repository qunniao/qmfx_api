package com.zhancheng.applet.controller;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.CartService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.OrderCartDTO;
import com.zhancheng.core.vo.CartConfirmListVO;
import com.zhancheng.core.vo.CartListVO;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.Cart;

import javax.annotation.Resource;


/**
 * 购物车
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Api(tags = "购物车")
@RestController
@RequestMapping("/carts")
public class CartController {

    @Resource
    private CartService cartService;

    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "分页查询购物车表列表")
    @GetMapping("/page")
    public R<IPage<CartListVO>> queryPage(PageDto<Cart> pageDto, Integer userId){

        return R.ok(cartService.queryPage(pageDto, userId));
    }

    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "结算购物车商品")
    @ApiImplicitParam(name = "cIds", value = "一个或多个购物车id集合,多个id用逗号分隔")
    @GetMapping("/info")
    public R<Map<String, Object>> queryInfo(@RequestParam List<Integer> cIds){

        return R.ok(cartService.queryInfo(cIds));
    }

    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "产品页面立即购买")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(name = "productId", value = "产品id",required = true),
            @ApiImplicitParam(name = "productNum", value = "产品数量", required = true)
    })
    @GetMapping("/queryProductOrderPrice")
    public R<Map<String, Object>> queryProductOrderPrice(Integer userId,Integer productId,Integer productNum){

        return R.ok(cartService.queryProductOrderPrice(userId,productId,productNum));
    }


   // @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "提交购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(name = "contactName", value = "联系人",required = true),
            @ApiImplicitParam(name = "contactPhone", value = "联系电话", required = true),
            @ApiImplicitParam(name = "contactAddress", value = "收货地址", required = true),
            @ApiImplicitParam(name = "freight", value = "运费:包邮就传0",required = true),
            @ApiImplicitParam(name = "remark", value = "备注留言"),
            @ApiImplicitParam(name = "deliveryWay", value = "配送方式1.快递2.无需物流3.自提；默认为1"),
            @ApiImplicitParam(name = "cidList", value = "购物车集合",required = true),
    })
    @PostMapping("/submitCart")
    public R<String> submitCart(@RequestBody OrderCartDTO orderCartDto){

        return R.ok(cartService.submitCart(orderCartDto));
    }

    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "添加购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购物车id,修改购物车时传入"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(name = "shareId", value = "分享人id"),
            @ApiImplicitParam(name = "productId", value = "商品id", required = true),
            @ApiImplicitParam(name = "productName", value = "商品名称", required = true),
            @ApiImplicitParam(name = "productPrice", value = "商品单价", required = true),
            @ApiImplicitParam(name = "productNum", value = "商品数量", required = true)
    })
    @PostMapping("/insertOrUpdate")
    public R<Boolean> insertOrUpdate(@RequestBody Cart cart){

        return R.ok(cartService.insertOrUpdate(cart));
    }

    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "添加或者减少数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "购物车id"),
            @ApiImplicitParam(name = "num", value = "增加后或减少后的数量"),
    })
    @PostMapping("/changeProductNum")
    public R<Boolean> changeProductNum(Integer cid, Integer num){

        return R.ok(cartService.changeProductNum(cid, num));
    }

    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "删除购物车表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> cids){

        return R.ok(cartService.removeByIds(cids));
    }

    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "查询购物车优惠金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(name = "cidList", value = "购物车集合",required = true)
    })
    @PostMapping("/queryCartDiscount")
    public R<Map<String,Object>> queryCartDiscount(@RequestBody OrderCartDTO orderCartDto){

        return R.ok(cartService.queryCartDiscount(orderCartDto));
    }

}
