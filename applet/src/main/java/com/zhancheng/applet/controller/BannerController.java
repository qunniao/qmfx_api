package com.zhancheng.applet.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.BannerService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.Banner;

import javax.annotation.Resource;


/**
 * 轮播图
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Api(tags = "轮播图")
@RestController
@RequestMapping("/banners")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @ApiOperation(value = "查询轮播图列表")
    @GetMapping("/list")
    public R<List<Banner>> queryList() {

        return R.ok(bannerService.list(new QueryWrapper<Banner>().orderByDesc("sort")));
    }
}
