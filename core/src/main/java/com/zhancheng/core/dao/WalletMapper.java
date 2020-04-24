package com.zhancheng.core.dao;

import com.zhancheng.core.entity.Wallet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.WalletVO;
import org.springframework.stereotype.Repository;

/**
 * 钱包
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface WalletMapper extends BaseMapper<Wallet> {

    /**
     * 查询钱包信息
     * @param userId
     * @return
     */
    WalletVO queryInfo(Integer userId);
}
