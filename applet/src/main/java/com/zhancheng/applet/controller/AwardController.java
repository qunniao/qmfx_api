package com.zhancheng.applet.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zhancheng.applet.service.AwardService;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.dto.UserDTO;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.vo.AwardVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "佣金")
@RestController
@RequestMapping("/awards")
public class AwardController {


    @Resource
    private AwardService awardService;


    @ApiOperation(value = "获取用户佣金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    })
    @PostMapping("/queryListByUserId")
    public R<List<AwardVO>> queryListByUserId(Integer userId) {

        R<List<AwardVO>> r = awardService.queryListByUserId(userId);
        return r;
    }
}
