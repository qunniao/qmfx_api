package com.zhancheng.applet.controller;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.WithdrawReviewService;
import com.zhancheng.core.commom.RedisTemplate;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.UserIdentity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;

import com.zhancheng.core.entity.WithdrawReview;

import javax.annotation.Resource;


/**
 * 提现审核
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "提现审核")
@RestController
@RequestMapping("/withdrawReviews")
@Verify(role = UserIdentity.ORDINARY)
public class WithdrawReviewController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private WithdrawReviewService withdrawReviewService;


    @ApiOperation(value = "添加提现审核")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "trueName", value = "姓名",required = true),
                        @ApiImplicitParam(name = "account", value = "提现账户",required = true),
                        @ApiImplicitParam(name = "amount", value = "提现金额",required = true)
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody WithdrawReview withdrawReview){

        Integer userUid = redisTemplate.getUserUid();
        withdrawReview.setUserId(userUid);
        return R.ok(withdrawReviewService.save(withdrawReview));
    }



}
