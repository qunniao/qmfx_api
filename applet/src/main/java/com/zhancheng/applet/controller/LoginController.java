package com.zhancheng.applet.controller;

import com.zhancheng.applet.service.LoginService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project LoginController
 * @date 2019/10/26 11:48
 */
@Api(tags = "登录接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @Verify
    @PostMapping("/wxLogin")
    @ApiOperation(value = "微信登录", notes = "传入从微信获得code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "凭证", required = true),
            @ApiImplicitParam(name = "encryptedData", value = "包括敏感数据在内的完整用户信息的加密数据", required = true),
            @ApiImplicitParam(name = "iv", value = "加密算法的初始向量", required = true),
            @ApiImplicitParam(name = "inviterId", value = "邀请人id")
    })
    public R<Map<String, Object>> wxLogin(RegisterDTO registerDto) throws Exception {

        return R.ok(loginService.wxLogin(registerDto));
    }
}
