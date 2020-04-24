package com.zhancheng.backstage.controller;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ConfigInfoService;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.UserIdentity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.commom.PageDto;

import com.zhancheng.core.entity.ConfigInfo;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;


/**
 * 奖励等配置信息
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "配置信息")
@RestController
@RequestMapping("/configInfos")
@Verify(role = UserIdentity.ADMIN)
public class ConfigInfoController {

    @Resource
    private ConfigInfoService configInfoService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询奖励等配置信息列表", notes = "share_award:商品分享奖励金额比例 free_num:免单规则奖励商品数" +
            " free_award:免单规则奖励比例 lower_num下级监管人数")
    @GetMapping("/list")
    public R<List<ConfigInfo>> queryList() {

        return R.ok(configInfoService.list());
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改奖励等配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id"),
            @ApiImplicitParam(name = "value", value = "配置信息")
    })
    @PutMapping("/update")
    public R<Boolean> update(@NotNull Integer id,@NotNull String value) {
        ConfigInfo configInfo = configInfoService.getById(id);

        if (ObjectUtil.isNull(configInfo)) {
           throw new MyException(CodeMsg.CONFIG_INFO_IS_NULL);
        }

        configInfo.setValue(value);

        return R.ok(configInfo.updateById());
    }

}
