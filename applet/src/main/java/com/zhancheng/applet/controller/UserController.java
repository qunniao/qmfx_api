package com.zhancheng.applet.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.dto.UserDTO;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.vo.UserListVO;
import com.zhancheng.core.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;


/**
 * 用户
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true),
            @ApiImplicitParam(name = "nickname", value = "昵称"),
            @ApiImplicitParam(name = "region", value = "地区"),
            @ApiImplicitParam(name = "gender", value = "性别"),
            @ApiImplicitParam(name = "birth", value = "出生年月"),
            @ApiImplicitParam(name = "phone", value = "电话"),
    })
    @PutMapping("/update")
    public R<Object> update(@Valid UserDTO userDTO) {

        User user = new User();
        BeanUtil.copyProperties(userDTO, user);

        user.updateById();
        return R.ok(userMapper.selectById(user.getId()));
    }

    @ApiOperation(value = "锁定用户邀请人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true),
            @ApiImplicitParam(name = "inviterId", value = "邀请人id")
    })
    @PostMapping("/lockInviter")
    public R<User> lockInviter(Integer id, Integer inviterId) {

        User user = userService.lockInviter(id,inviterId);
        return R.ok(user);
    }
}
