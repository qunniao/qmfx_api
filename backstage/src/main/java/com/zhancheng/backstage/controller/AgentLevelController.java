package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.AgentLevelService;
import com.zhancheng.backstage.service.WalletHistoryService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.WalletHistoryQueryDTO;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.WalletHistory;
import com.zhancheng.core.vo.WalletHistoryListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 代理等级
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-28 18:14:10
 */
@Api(tags = "代理等级")
@RestController
@RequestMapping("/agentLevel")
public class AgentLevelController {

    @Resource
    private AgentLevelService agentLevelService;

    @ApiOperation(value = "查询代理等级列表")
    @GetMapping("/page")
    public R<IPage<AgentLevel>> queryPage(PageDto<AgentLevel> pageDto) {

        return R.ok(agentLevelService.queryPage(pageDto));
    }


    @ApiOperation(value = "查询代理等级详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<AgentLevel> queryInfo(@PathVariable("id") Integer id) {

        return R.ok(agentLevelService.queryInfo(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改等级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "minStock", value = "最近进货额")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody AgentLevel agentLevel){

        return R.ok(agentLevelService.update(agentLevel));
    }
}
