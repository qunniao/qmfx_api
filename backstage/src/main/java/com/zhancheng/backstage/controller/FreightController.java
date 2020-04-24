package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.FreightService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.entity.Freight;
import com.zhancheng.core.vo.FreightListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-10-24 10:18:14
 */
@Api(tags = "运费模板")
@RestController
@RequestMapping("/freights")
public class FreightController {

    @Resource
    private FreightService freightService;

    @ApiOperation(value = "分页查询运费模板列表")
    @GetMapping("/list")
    public R<IPage<FreightListVo>> list(PageDto<Freight> pageDto) {

        return R.ok(freightService.selectPage(pageDto));
    }

    @ApiOperation(value = "查询运费模板详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<Freight> info(@PathVariable("id") Integer id) {

        return R.ok(freightService.info(id));
    }

    @ApiOperation(value = "添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "模版名称"),
            @ApiImplicitParam(name = "valuationWay", value = "计价方式1.按重量2.按件数"),
            @ApiImplicitParam(name = "defaultStock", value = "默认采购运费1.是0.否"),
            @ApiImplicitParam(name = "defaultBuyins", value = "默认内购运费1.是0.否"),
            @ApiImplicitParam(name = "defaultTake", value = "默认提货运费1.是0.否"),
            @ApiImplicitParam(name = "defaultRetail", value = "默认零售运费1.是0.否"),
            @ApiImplicitParam(name = "transportWay", value = "运送方式（默认）"),
            @ApiImplicitParam(name = "freightRegionList", value = "配送区域集合")
    })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody Freight freight) {

        return R.ok(freightService.insert(freight));
    }

    @ApiOperation(value = "修改运费模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "运费模板id"),
            @ApiImplicitParam(name = "title", value = "模版名称"),
            @ApiImplicitParam(name = "valuationWay", value = "计价方式1.按重量2.按件数"),
            @ApiImplicitParam(name = "defaultStock", value = "默认采购运费1.是0.否"),
            @ApiImplicitParam(name = "defaultBuyins", value = "默认内购运费1.是0.否"),
            @ApiImplicitParam(name = "defaultTake", value = "默认提货运费1.是0.否"),
            @ApiImplicitParam(name = "defaultRetail", value = "默认零售运费1.是0.否"),
            @ApiImplicitParam(name = "transportWay", value = "运送方式（默认）"),
            @ApiImplicitParam(name = "status", value = "状态"),
            @ApiImplicitParam(name = "gmtCreate", value = "创建时间"),
            @ApiImplicitParam(name = "gmtModified", value = "修改时间"),
            @ApiImplicitParam(name = "freightRegionList", value = "配送区域集合")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody Freight freight) {

        return R.ok(freightService.update(freight));
    }

    @ApiOperation(value = "删除运费模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id"),
    })
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Integer id) {

        return R.ok(freightService.delete(id));
    }

}
