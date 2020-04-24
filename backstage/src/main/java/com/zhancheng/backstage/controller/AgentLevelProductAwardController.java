package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.AgentLevelProductAwardService;
import com.zhancheng.backstage.service.AgentLevelService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.AgentLevelProductAwardQueryDTO;
import com.zhancheng.core.dto.OrderQueryDTO;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.entity.AgentLevelProductAward;
import com.zhancheng.core.vo.AgentLevelProductAwardListVO;
import com.zhancheng.core.vo.AgentLevelProductAwardVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 代理等级产品佣金
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-28 18:14:10
 */
@Api(tags = "代理等级产品佣金")
@RestController
@RequestMapping("/agentLevelProductAward")
public class AgentLevelProductAwardController {

    @Resource
    private AgentLevelProductAwardService agentLevelProductAwardService;

    @ApiOperation(value = "查询代理等级产品佣金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agentLevelId", value = "代理等级id")
    })
    @GetMapping("/page")
    public R<IPage<AgentLevelProductAwardListVO>> queryPage(PageDto<AgentLevelProductAward> pageDto, AgentLevelProductAwardQueryDTO queryDto) {

        return R.ok(agentLevelProductAwardService.queryPage(pageDto,queryDto));
    }



    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改等级产品佣金")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "agentLevelId", value = "代理等级id"),
            @ApiImplicitParam(name = "productId", value = "产品id"),
            @ApiImplicitParam(name = "awardMoney", value = "佣金金额"),
            @ApiImplicitParam(name = "agentPrice", value = "代理价格")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody AgentLevelProductAward agentLevel){

        return R.ok(agentLevelProductAwardService.update(agentLevel));
    }
}
