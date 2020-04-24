package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.AwardService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AwardQueryDTO;
import com.zhancheng.core.dto.WalletHistoryQueryDTO;
import com.zhancheng.core.entity.WalletHistory;
import com.zhancheng.core.vo.AwardVO;
import com.zhancheng.core.vo.WalletHistoryListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 用户
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "佣金")
@RestController
@RequestMapping("/awards")
public class AwardController {


    @Resource
    private AwardService awardService;


    @ApiOperation(value = "获取用户佣金列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    })
    @PostMapping("/queryListByUserId")
    public R<List<AwardVO>> queryListByUserId(Integer userId) {

        R<List<AwardVO>> r = awardService.queryListByUserId(userId);
        return r;
    }

    @ApiOperation(value = "查询奖励记录")
    @ApiImplicitParam(name = "userId", value = "用户id")
    @GetMapping("/page")
    public R<IPage<AwardVO>> queryPage(PageDto<WalletHistory> pageDto, AwardQueryDTO awardQueryDTO) {

        return R.ok(awardService.queryPage(pageDto, awardQueryDTO));
    }
}
