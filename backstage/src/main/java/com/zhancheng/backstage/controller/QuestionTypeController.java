package com.zhancheng.backstage.controller;


import com.zhancheng.backstage.service.QuestionTypeService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;

import com.zhancheng.core.entity.QuestionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 问题分类
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
@Api(tags = "问题分类")
@RestController
@RequestMapping("/questionTypes")
@Verify(role = UserIdentity.ADMIN)
public class QuestionTypeController {

    @Resource
    private QuestionTypeService questionTypeService;


    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询产品类目列表")
    @GetMapping("/list")
    public R<List<Map<String, Object>>> list(){

        return R.ok(questionTypeService.list());
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询类型详情")
    @ApiImplicitParam(name = "id", value = "类型id")
    @GetMapping("/info/{id}")
    public R<QuestionType> queryInfo(@PathVariable("id") Integer id){

        return R.ok(questionTypeService.getById(id));
    }




    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加商品类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "类型名"),
            @ApiImplicitParam(name = "sort", value = "排序")
    })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody QuestionType questionType){

        return R.ok(questionTypeService.save(questionType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类型id", required = true),
            @ApiImplicitParam(name = "name", value = "类型名"),
            @ApiImplicitParam(name = "sort", value = "排序")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody QuestionType questionType){

        return R.ok(questionTypeService.updateById(questionType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类型id"),
    })
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Integer id){

        return R.ok(questionTypeService.removeById(id));
    }


}
