package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.AgentLevelService;
import com.zhancheng.backstage.service.AwardService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.AgentLevelMapper;
import com.zhancheng.core.dao.AwardMapper;
import com.zhancheng.core.dto.AwardQueryDTO;
import com.zhancheng.core.entity.AgentLevel;
import com.zhancheng.core.entity.Award;
import com.zhancheng.core.entity.WalletHistory;
import com.zhancheng.core.vo.AwardVO;
import com.zhancheng.core.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 奖励
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */

@Service
public class AgentLevelServiceImpl extends ServiceImpl<AgentLevelMapper, AgentLevel> implements AgentLevelService {
    private static Logger log = LoggerFactory.getLogger(AgentLevelServiceImpl.class);

    @Resource
    private AgentLevelMapper agentLevelMapper;

    @Override
    public IPage<AgentLevel> queryPage(PageDto<AgentLevel> pageDto) {
        return baseMapper.queryPage(pageDto.getPage());
    }

    @Override
    public AgentLevel queryInfo(Integer id) {
        AgentLevel item = baseMapper.queryInfo(id);

        if (ObjectUtil.isNull(item)) {
            throw new MyException(CodeMsg.NULL_POINTER_EXCEPTION);
        }
        return item;
    }

    @Override
    public Boolean update(AgentLevel agentLevel) {
        log.info("修改代理等级 minStock:"+agentLevel.getMinStock()+" id:"+agentLevel.getId());
        AgentLevel item = baseMapper.queryInfo(agentLevel.getId());
        if (ObjectUtil.isNull(item)) {
            throw new MyException(CodeMsg.NULL_POINTER_EXCEPTION);
        }
        item.setMinStock(agentLevel.getMinStock());
        item.updateById();
        return true;
    }
}