package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zhancheng.backstage.service.AwardService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.AwardMapper;
import com.zhancheng.core.dto.AwardQueryDTO;
import com.zhancheng.core.dto.WalletHistoryQueryDTO;
import com.zhancheng.core.entity.Award;
import com.zhancheng.core.entity.WalletHistory;
import com.zhancheng.core.vo.AwardVO;
import com.zhancheng.core.vo.WalletHistoryListVO;
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
public class AwardServiceImpl extends ServiceImpl<AwardMapper, Award> implements AwardService {
    private static Logger log = LoggerFactory.getLogger(AwardServiceImpl.class);

    @Resource
    private AwardMapper awardMapper;

    @Override
    public R<List<AwardVO>> queryListByUserId(Integer userId) {
        log.info("queryListByUserId userId:"+userId);
        List<AwardVO> list = awardMapper.queryListByUserId(userId);
        log.info("queryListByUserId list size:"+list.size());
        return R.ok(list);
    }

    @Override
    public IPage<AwardVO> queryPage(PageDto<WalletHistory> pageDto, AwardQueryDTO awardQueryDTO) {
        return baseMapper.queryPage(pageDto.getPage(), awardQueryDTO);
    }
}