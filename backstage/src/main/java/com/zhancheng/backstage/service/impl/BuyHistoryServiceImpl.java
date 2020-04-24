package com.zhancheng.backstage.service.impl;

import com.zhancheng.backstage.service.BuyHistoryService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.BuyHistoryMapper;
import com.zhancheng.core.entity.BuyHistory;

/**
 * 商品购买记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */

@Service
public class BuyHistoryServiceImpl extends ServiceImpl<BuyHistoryMapper, BuyHistory> implements BuyHistoryService {

}