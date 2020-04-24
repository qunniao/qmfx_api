package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.AgentLevelProductAwardService;
import com.zhancheng.backstage.service.AgentLevelService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.AgentLevelMapper;
import com.zhancheng.core.dao.AgentLevelProductAwardMapper;
import com.zhancheng.core.dto.AgentLevelProductAwardQueryDTO;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.entity.AgentLevelProductAward;
import com.zhancheng.core.vo.AgentLevelProductAwardListVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 奖励
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */

@Service
public class AgentLevelProductAwardServiceImpl extends ServiceImpl<AgentLevelProductAwardMapper, AgentLevelProductAward> implements AgentLevelProductAwardService {
    private static Logger log = LoggerFactory.getLogger(AgentLevelProductAwardServiceImpl.class);

    @Resource
    private AgentLevelProductAwardMapper agentLevelProductAwardMapper;

    @Override
    public IPage<AgentLevelProductAwardListVO> queryPage(PageDto<AgentLevelProductAward> pageDto, AgentLevelProductAwardQueryDTO queryDto) {
        return agentLevelProductAwardMapper.queryPage(pageDto.getPage(),queryDto);
    }



    @Override
    public Boolean update(AgentLevelProductAward agentLevelProductAward) {
        log.info("修改代理等级产品佣金 agentLevelId:"+agentLevelProductAward.getAgentLevelId()+" productId:"+agentLevelProductAward.getProductId()+" awardMoney:"+agentLevelProductAward.getAwardMoney());
        AgentLevelProductAward item = agentLevelProductAwardMapper.queryInfo(agentLevelProductAward.getAgentLevelId(),agentLevelProductAward.getProductId());
        if (ObjectUtil.isNull(item)) {
            item = new AgentLevelProductAward();
            item.setAgentLevelId(agentLevelProductAward.getAgentLevelId());
            item.setAwardMoney(agentLevelProductAward.getAwardMoney());
            item.setAgentPrice(agentLevelProductAward.getAgentPrice());
            item.setProductId(agentLevelProductAward.getProductId());
            item.insert();
        }else{
            item.setAwardMoney(agentLevelProductAward.getAwardMoney());
            item.setAgentPrice(agentLevelProductAward.getAgentPrice());
            item.updateById();
        }

        return true;
    }
}