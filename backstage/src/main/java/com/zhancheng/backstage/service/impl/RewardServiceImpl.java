package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.RewardService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.vo.RewardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project RewardServiceImpl
 * @date 2019/11/26 13:50
 */
@Service
public class RewardServiceImpl implements RewardService {

    @Resource
    private UserMapper userMapper;

    @Override
    public IPage<RewardVO> queryLower(PageDto pageDto, Integer inviterId) {

        IPage<RewardVO> rewardPage = userMapper.queryLower(pageDto.getPage(), inviterId);

        List<RewardVO> records = rewardPage.getRecords();
        if (ObjectUtil.isNotNull(records)) {
            for (RewardVO rewardVO : records) {
                Integer lowerRecentlySales = userMapper.queryLowerRecentlySales(rewardVO.getUserId());
                rewardVO.setLowerRecentlySales(lowerRecentlySales);
            }
        }
        return rewardPage;
    }
}
