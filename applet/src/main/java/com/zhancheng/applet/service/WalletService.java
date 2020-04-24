package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.entity.Wallet;
import com.zhancheng.core.vo.WalletVO;

/**
 * 钱包
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
public interface WalletService extends IService<Wallet> {

    /**
     * 查询钱包信息
     * @param userId
     * @return
     */
    WalletVO queryInfo(Integer userId);

}

