package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.AgentLevelService;
import com.zhancheng.backstage.service.QuestionService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.ProductQueryDTO;
import com.zhancheng.core.dto.QuestionQueryDTO;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.entity.Question;
import com.zhancheng.core.vo.QuestionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 问题
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-28 18:14:10
 */
@Api(tags = "帮助中心问题")
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @ApiOperation(value = "查询问题列表")
    @GetMapping("/page")
    public R<IPage<QuestionVO>> queryPage(PageDto<Question> pageDto, QuestionQueryDTO queryDto) {

        return R.ok(questionService.queryPage(pageDto,queryDto));
    }


    @ApiOperation(value = "查询详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<QuestionVO> queryInfo(@PathVariable("id") Integer id) {

        return R.ok(questionService.queryInfo(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "typeId", value = "类型id"),
            @ApiImplicitParam(name = "title", value = "问题标题"),
            @ApiImplicitParam(name = "cover", value = "封面图"),
            @ApiImplicitParam(name = "content", value = "问题回答内容"),
            @ApiImplicitParam(name = "sort", value = "排序")
    })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody Question item){

        return R.ok(questionService.save(item));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "typeId", value = "类型id"),
            @ApiImplicitParam(name = "title", value = "问题标题"),
            @ApiImplicitParam(name = "cover", value = "封面图"),
            @ApiImplicitParam(name = "content", value = "问题回答内容"),
            @ApiImplicitParam(name = "sort", value = "排序")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody Question item){

        return R.ok(questionService.update(item));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pids", value = "主键id")
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> ids){

        return R.ok(questionService.delete(ids));
    }

}
