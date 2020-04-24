package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.Freight;
import com.zhancheng.core.vo.FreightListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-24 10:18:14
 */
@Repository
public interface FreightMapper extends BaseMapper<Freight> {

    /**
     * 分页查询数据
     * @param page 分页数据
     * @return IPage<FreightListVo>
     */
    IPage<FreightListVo> queryPage(Page page);

    /**
     * 查询 运费详情
     * @param id
     * @return
     */
    Freight queryInfo(@Param("id") Integer id);
	
}
