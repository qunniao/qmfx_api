package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.AgentLevelProductAwardQueryDTO;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.entity.AgentLevelProductAward;
import com.zhancheng.core.vo.AgentLevelProductAwardListVO;


/**
 * 奖励
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
public interface AgentLevelProductAwardService extends IService<AgentLevelProductAward> {

    IPage<AgentLevelProductAwardListVO> queryPage(PageDto<AgentLevelProductAward> pageDto, AgentLevelProductAwardQueryDTO queryDto);


    Boolean update(AgentLevelProductAward agentLevelProductAward);
}

