package com.zhancheng.applet.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.TransferService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.TransferQueryDTO;
import com.zhancheng.core.entity.Transfer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BianShuHeng
 * @decription
 * @project TransferController
 * @date 2019/11/8 15:31
 */
@Api(tags = "转账")
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Verify(role = UserIdentity.ORDINARY)
    @ApiOperation(value = "分页查询零钱明细")
    @GetMapping("/page")
    public R<IPage<Transfer>> queryPage(PageDto pageDto, TransferQueryDTO transferQueryDto){

        return R.ok(transferService.selectPage(pageDto, transferQueryDto));
    }


}
