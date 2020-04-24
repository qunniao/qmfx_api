package com.zhancheng.applet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.applet.service.UserAddressService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.UserAddress;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 用户地址
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-10-25 16:51:16
 */
@Api(tags = "用户地址")
@RestController
@RequestMapping("/userAddress")
@Verify(role = UserIdentity.ORDINARY)
public class UserAddressController {

    @Resource
    private UserAddressService userAddressService;

    @ApiOperation(value = "分页查询用户地址列表")
    @ApiImplicitParam(name = "uid", value = "用户id")
    @GetMapping("/list/{uid}")
    public R list(@PathVariable Integer uid) {

        return R.ok(userAddressService.list(new QueryWrapper<UserAddress>().eq("user_id", uid)
                .orderByDesc("is_default").orderByDesc("gmt_modified")));
    }

    @ApiOperation(value = "查询用户地址详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {

        return R.ok(userAddressService.info(id));
    }

    @ApiOperation(value = "添加用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "contactName", value = "联系人姓名"),
            @ApiImplicitParam(name = "contactPhone", value = "联系人电话"),
            @ApiImplicitParam(name = "contactAddress", value = "地址"),
            @ApiImplicitParam(name = "city", value = "市"),
            @ApiImplicitParam(name = "county", value = "区"),
            @ApiImplicitParam(name = "provinceId", value = "省id"),
            @ApiImplicitParam(name = "cityId", value = "城市id"),
            @ApiImplicitParam(name = "countyId", value = "区域id"),
    })
    @PostMapping("/save")
    public R save(@RequestBody UserAddress userAddress) {

        return R.ok(userAddressService.insert(userAddress));
    }

    @ApiOperation(value = "修改用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户地址id"),
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "contactName", value = "联系人姓名"),
            @ApiImplicitParam(name = "contactPhone", value = "联系人电话"),
            @ApiImplicitParam(name = "contactAddress", value = "地址"),
            @ApiImplicitParam(name = "city", value = "市"),
            @ApiImplicitParam(name = "county", value = "区"),
            @ApiImplicitParam(name = "provinceId", value = "省id"),
            @ApiImplicitParam(name = "cityId", value = "城市id"),
            @ApiImplicitParam(name = "countyId", value = "区域id"),
            @ApiImplicitParam(name = "isDefault", value = "是否为默认地址,0否，1是")
    })
    @PutMapping("/update")
    public R update(@RequestBody UserAddress userAddress) {

        return R.ok(userAddressService.update(userAddress));
    }

    @ApiOperation(value = "查询默认收货地址")
    @ApiImplicitParam(name = "uid", value = "用户id")
    @GetMapping("/queryDefault/{uid}")
    public R queryDefault(@PathVariable Integer uid) {

        return R.ok(userAddressService.queryDefault(uid));
    }

    @ApiOperation(value = "删除用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id")
    })
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id) {

        return R.ok(userAddressService.removeById(id));
    }

}
