package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.WalletService;
import com.zhancheng.core.dao.WalletMapper;
import com.zhancheng.core.entity.Wallet;
import org.springframework.stereotype.Service;

/**
 * 钱包
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements WalletService {

}