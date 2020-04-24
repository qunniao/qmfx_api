package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.FreightRegion;

import java.util.List;

/**
 * 运费地区
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-24 10:18:15
 */
public interface FreightRegionService extends IService<FreightRegion> {

    /**
     * 分页查询运费地区列表
     * @param pageDto 分页参数
     * @return IPage<FreightRegion>
     */
    IPage<FreightRegion> selectPage(PageDto<FreightRegion> pageDto);

    /**
     * 查询运费地区详情
     * @param id 主键Id
     * @return
     */
    FreightRegion info(Integer id);

    /**
     * 添加运费地区
     * @param freightRegion 运费地区数据
     * @return Boolean
     */
    Boolean insert(FreightRegion freightRegion);

    /**
     * 修改运费地区
     * @param freightRegion 运费地区数据
     * @return Boolean
     */
    Boolean update(FreightRegion freightRegion);

    /**
     * 批量删除运费地区
     * @param ids 运费地区id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> ids);
}

