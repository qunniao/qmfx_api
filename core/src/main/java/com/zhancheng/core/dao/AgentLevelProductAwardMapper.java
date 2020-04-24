package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.AgentLevelProductAwardQueryDTO;
import com.zhancheng.core.entity.Admin;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.entity.AgentLevelProductAward;
import com.zhancheng.core.vo.AgentLevelProductAwardListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 管理员
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface AgentLevelProductAwardMapper extends BaseMapper<AgentLevelProductAward> {

    AgentLevelProductAward queryInfo(Integer agentLevelId, Integer productId);

    IPage<AgentLevelProductAwardListVO> queryPage(Page<AgentLevelProductAward> page, @Param("query") AgentLevelProductAwardQueryDTO queryDto);
}
