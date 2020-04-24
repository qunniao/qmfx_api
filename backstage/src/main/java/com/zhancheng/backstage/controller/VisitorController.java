package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.VisitorService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.vo.VisitorListVO;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;


import com.zhancheng.core.entity.Visitor;

import javax.annotation.Resource;


/**
 * 访客记录
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-23 11:31:19
 */
@Api(tags = "访客记录")
@RestController
@RequestMapping("/visitors")
public class VisitorController {

    @Resource
    private VisitorService visitorService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询访客记录列表")
    @GetMapping("/page")
    public R<IPage<VisitorListVO>> queryPage(PageDto<Visitor> pageDto){

        return R.ok(visitorService.queryPage(pageDto));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除访客记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id"),
    })
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Integer id){

        return R.ok(visitorService.removeById(id));
    }

}
