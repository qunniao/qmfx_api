package com.zhancheng.applet.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.UserCloudProductService;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.dto.UserDTO;
import com.zhancheng.core.entity.Cart;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.entity.UserCloudProduct;
import com.zhancheng.core.vo.CartListVO;
import com.zhancheng.core.vo.UserCloudProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * 用户
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "用户云产品")
@RestController
@RequestMapping("/userCloudProduct")
public class UserCloudProductController {


    @Resource
    private UserCloudProductService userCloudProductService;

    @ApiOperation(value = "查询云产品列表")
    @GetMapping("/list")
    public R<List<UserCloudProductVO>> list(Integer userId){

        return R.ok(userCloudProductService.list(userId));
    }
}
