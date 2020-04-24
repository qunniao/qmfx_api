package com.zhancheng.backstage.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.backstage.service.AdminService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.Admin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-21 11:00:45
 */
@Api(tags = "管理员")
@RestController
@RequestMapping("/admins")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private RedisTemplate redisTemplate;

    @Verify
    @PostMapping("/login")
    @ApiOperation(value = "管理员登录", notes = "用户名登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true)
    })
    public R login(String username, String pwd) {

        Map<String, Object> map = new HashMap<>(2);
        Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("username", username)
                .eq("pwd", SecureUtil.md5(pwd)));
        System.err.println(admin);
        if (ObjectUtil.isNotNull(admin)) {
            String token = redisTemplate.setAdmin(admin);
            admin.updateById();
            map.put("token", token);
            map.put("admin",admin);
            return R.ok(map);
        } else {
            return R.fail(CodeMsg.PASSWORD_ERROR);
        }
    }



}
