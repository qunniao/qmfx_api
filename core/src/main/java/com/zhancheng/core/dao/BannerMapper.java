package com.zhancheng.core.dao;

import com.zhancheng.core.entity.Banner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 轮播图
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Repository
public interface BannerMapper extends BaseMapper<Banner> {
	
}
