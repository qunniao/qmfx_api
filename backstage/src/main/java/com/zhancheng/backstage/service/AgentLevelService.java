package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.AgentLevel;


/**
 * 奖励
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
public interface AgentLevelService extends IService<AgentLevel> {

    IPage<AgentLevel> queryPage(PageDto<AgentLevel> pageDto);

    AgentLevel queryInfo(Integer id);

    Boolean update(AgentLevel agentLevel);
}

