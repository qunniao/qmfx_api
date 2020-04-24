package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.ConfigInfoService;
import com.zhancheng.core.dao.ConfigInfoMapper;
import com.zhancheng.core.entity.ConfigInfo;
import org.springframework.stereotype.Service;

/**
 * 奖励等配置信息
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class ConfigInfoServiceImpl extends ServiceImpl<ConfigInfoMapper, ConfigInfo> implements ConfigInfoService {

}