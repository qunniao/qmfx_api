package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.WalletHistoryQueryDTO;
import com.zhancheng.core.entity.WalletHistory;
import com.zhancheng.core.vo.WalletHistoryListVO;

/**
 * 钱包收支明细记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-28 18:14:10
 */
public interface WalletHistoryService extends IService<WalletHistory> {


    /**
     * 分页查询 钱包收支明细记录
     * @param pageDto 分页信息
     * @param walletHistoryQueryDTO 查询数据
     * @return
     */
    IPage<WalletHistoryListVO> queryPage(PageDto<WalletHistory> pageDto, WalletHistoryQueryDTO walletHistoryQueryDTO);

    /**
     * 添加钱包收支明细
     * @param item 钱包收支明细
     * @return Boolean
     */
    Boolean insert(WalletHistory item);
}

