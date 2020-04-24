package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.FreightRegion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 运费地区
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-24 10:18:15
 */
@Repository
public interface FreightRegionMapper extends BaseMapper<FreightRegion> {

    /**
     * 根据模板id 删除运费地区数据
     * @param fid
     * @return
     */
    Boolean deleteByFid(@Param("fid") Integer fid);
}
