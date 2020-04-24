package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.UserService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dao.WalletMapper;
import com.zhancheng.core.dto.UserDTO;
import com.zhancheng.core.entity.AgentLevelProductAward;
import com.zhancheng.core.entity.Wallet;
import com.zhancheng.core.vo.UserAgentListVO;
import com.zhancheng.core.vo.UserListVO;
import com.zhancheng.core.vo.UserVO;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.User;


import javax.annotation.Resource;


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
    private UserService userService;

    @Resource
    private WalletMapper walletMapper;

    //@Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询用户列表")
    @GetMapping("/page")
    public R<IPage<UserListVO>> queryPage(PageDto<User> pageDto) {

        return R.ok(userService.queryPage(pageDto));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询用户详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<UserVO> queryInfo(@PathVariable("id") Integer id) {

        return R.ok(userService.queryInfo(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id"),
    })
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {
        //删除用户和钱包
        userService.removeById(id);
        walletMapper.delete(new QueryWrapper<Wallet>().eq("user_id", id));
        return R.ok(userService.removeById(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询代理人信息列表")
    @GetMapping("/queryListByAgentInfo")
    public R<IPage<UserAgentListVO>> queryListByAgentInfo(PageDto<User> pageDto) {

        return R.ok(userService.queryListByAgentInfo(pageDto));
    }

   // @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "代理等级id"),
            @ApiImplicitParam(name = "agentLevel", value = "代理等级")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody UserDTO userDTO){

        return R.ok(userService.update(userDTO));
    }
}
