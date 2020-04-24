package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.AwardQueryDTO;
import com.zhancheng.core.entity.Award;
import com.zhancheng.core.entity.WalletHistory;
import com.zhancheng.core.vo.AwardVO;

import java.util.List;

/**
 * 奖励
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
public interface AwardService extends IService<Award> {

    R<List<AwardVO>> queryListByUserId(Integer userId);

    IPage<AwardVO> queryPage(PageDto<WalletHistory> pageDto, AwardQueryDTO awardQueryDTO);
}

