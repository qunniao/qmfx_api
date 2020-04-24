package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.Transfer;
import com.zhancheng.core.vo.CommissionListVO;

/**
 * 钱包收支流水
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
public interface TransferService extends IService<Transfer> {

    /**
     * 查询佣金明细
     * @param pageDto
     * @param userId
     * @return
     */
    IPage<CommissionListVO> queryCommission(PageDto<Transfer> pageDto, Integer userId);

}

