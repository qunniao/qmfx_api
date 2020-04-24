package com.zhancheng.applet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.applet.service.WalletService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.Wallet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 钱包
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "钱包")
@RestController
@RequestMapping("/wallets")
@Verify(role = UserIdentity.ORDINARY)
public class WalletController {

    @Resource
    private WalletService walletService;

    @ApiOperation(value = "查询钱包详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{userId}")
    public R<Wallet> queryInfo(@PathVariable Integer userId){

        return R.ok(walletService.queryInfo(userId));
    }




}
