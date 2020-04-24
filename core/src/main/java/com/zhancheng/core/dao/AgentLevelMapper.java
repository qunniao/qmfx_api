package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.Admin;
import com.zhancheng.core.entity.AgentLevel;
import org.springframework.stereotype.Repository;

/**
 * 代理等级
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface AgentLevelMapper extends BaseMapper<AgentLevel> {

    IPage<AgentLevel> queryPage(Page<AgentLevel> page);

    AgentLevel queryInfo(Integer id);
}
