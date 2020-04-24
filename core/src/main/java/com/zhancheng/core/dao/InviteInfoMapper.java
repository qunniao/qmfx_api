package com.zhancheng.core.dao;

import com.zhancheng.core.entity.InviteInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 邀请人层级信息
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 18:10:02
 */
@Repository
public interface InviteInfoMapper extends BaseMapper<InviteInfo> {
	
}
