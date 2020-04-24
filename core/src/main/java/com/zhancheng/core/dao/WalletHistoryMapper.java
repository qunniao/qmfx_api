package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.WalletHistoryQueryDTO;
import com.zhancheng.core.entity.Wallet;
import com.zhancheng.core.entity.WalletHistory;
import com.zhancheng.core.vo.WalletHistoryListVO;
import com.zhancheng.core.vo.WalletVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 钱包
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface WalletHistoryMapper extends BaseMapper<WalletHistory> {
    IPage<WalletHistoryListVO> queryPage(Page page, @Param("query") WalletHistoryQueryDTO walletHistoryQueryDTO);
}
