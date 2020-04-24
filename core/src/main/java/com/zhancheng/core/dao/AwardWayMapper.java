package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.Award;
import com.zhancheng.core.entity.AwardWay;
import org.springframework.stereotype.Repository;

/**
 * 奖励
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Repository
public interface AwardWayMapper extends BaseMapper<AwardWay> {

    AwardWay queryInfo(Integer pid);
}
