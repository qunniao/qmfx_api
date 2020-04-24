package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ConfigInfoService;
import com.zhancheng.backstage.service.RewardService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.ConfigInfo;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.vo.RewardVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project RewardController
 * @date 2019/11/26 10:50
 */
@Api(tags = "奖励")
@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Resource
    private RewardService rewardService;

//    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询下级列表")
    @ApiImplicitParam(name = "inviterId", value = "用户id")
    @GetMapping("/list")
    public R<IPage<RewardVO>> queryLower(PageDto<User> pageDto, Integer inviterId) {

        return R.ok(rewardService.queryLower(pageDto, inviterId));
    }
}
