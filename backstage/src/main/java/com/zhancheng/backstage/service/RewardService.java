package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.vo.RewardVO;


/**
 * @author BianShuHeng
 * @decription
 * @project RewardService
 * @date 2019/11/26 10:51
 */
public interface RewardService {

    /**
     * 查询下级列表
     * @param pageDto
     * @param inviterId 邀请人id
     * @return
     */
   IPage<RewardVO> queryLower(PageDto pageDto, Integer inviterId);
}
