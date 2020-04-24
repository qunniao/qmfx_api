package com.zhancheng.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.FreightService;
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
@Api(tags = "运费")
@RestController
@RequestMapping("/freights")
public class FreightController {

    @Resource
    private FreightService freightService;


    @ApiOperation(value = "查询运费模板详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<Freight> info(@PathVariable("id") Integer id) {

        return R.ok(freightService.info(id));
    }


}
