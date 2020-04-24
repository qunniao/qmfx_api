package com.zhancheng.applet.service.impl;

import com.zhancheng.applet.service.AwardService;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dao.CartMapper;
import com.zhancheng.core.vo.AwardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.AwardMapper;
import com.zhancheng.core.entity.Award;

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
}