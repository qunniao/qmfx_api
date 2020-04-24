package com.zhancheng.applet.service.impl;

import com.zhancheng.applet.service.WalletService;
import com.zhancheng.core.vo.WalletVO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.WalletMapper;
import com.zhancheng.core.entity.Wallet;

/**
 * 钱包
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements WalletService {

    @Override
    public WalletVO queryInfo(Integer userId) {
        return baseMapper.queryInfo(userId);
    }
}