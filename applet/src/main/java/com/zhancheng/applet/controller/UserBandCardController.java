package com.zhancheng.applet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.applet.service.UserAddressService;
import com.zhancheng.applet.service.UserBandCardService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.UserBandCardDTO;
import com.zhancheng.core.entity.UserAddress;
import com.zhancheng.core.entity.UserBandCard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 用户银行卡
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-10-25 16:51:16
 */
@Api(tags = "用户银行卡")
@RestController
@RequestMapping("/userBandCard")
@Verify(role = UserIdentity.ORDINARY)
public class UserBandCardController {

    @Resource
    private UserBandCardService userBandCardService;

    @ApiOperation(value = "分页查询列表")
    @ApiImplicitParam(name = "uid", value = "用户id")
    @GetMapping("/list/{uid}")
    public R list(@PathVariable Integer uid) {

        return R.ok(userBandCardService.list(new QueryWrapper<UserBandCard>().eq("user_id", uid)
                .orderByDesc("gmt_modified")));
    }

    @ApiOperation(value = "查询详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {

        return R.ok(userBandCardService.info(id));
    }

    @ApiOperation(value = "添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "realName", value = "真实姓名"),
            @ApiImplicitParam(name = "cardNumber", value = "卡号")
    })
    @PostMapping("/save")
    public R save(@RequestBody UserBandCardDTO userBandCard) {

        return R.ok(userBandCardService.insert(userBandCard));
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户地址id"),
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "realName", value = "真实姓名"),
            @ApiImplicitParam(name = "cardNumber", value = "卡号")
    })
    @PutMapping("/update")
    public R update(@RequestBody UserBandCardDTO userBandCard) {

        return R.ok(userBandCardService.update(userBandCard));
    }


    @ApiOperation(value = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id")
    })
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id) {

        return R.ok(userBandCardService.removeById(id));
    }

}
