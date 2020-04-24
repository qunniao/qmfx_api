package com.zhancheng.backstage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.TransferService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.Transfer;
import com.zhancheng.core.vo.CommissionListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 钱包收支流水
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "钱包收支流水")
@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Resource
    private TransferService transferService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询佣金明细")
    @GetMapping("/page/commission")
    public R<IPage<CommissionListVO>> queryCommission(PageDto<Transfer> pageDto, Integer userId){

        return R.ok(transferService.queryCommission(pageDto, userId));
    }
}
