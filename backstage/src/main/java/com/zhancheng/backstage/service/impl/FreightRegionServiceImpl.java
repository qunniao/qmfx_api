package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.FreightRegionService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.FreightRegionMapper;
import com.zhancheng.core.entity.FreightRegion;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运费地区
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-24 10:18:15
 */

@Service
public class FreightRegionServiceImpl extends ServiceImpl<FreightRegionMapper, FreightRegion> implements FreightRegionService {

    @Override
    public IPage<FreightRegion> selectPage(PageDto<FreightRegion> pageDto) {
        return baseMapper.selectPage(pageDto.getPage(),
                new QueryWrapper<FreightRegion>().eq("is_deleted", 0));
    }

    @Override
    public FreightRegion info(Integer id) {

        return baseMapper.selectById(id);
    }

    @Override
    public Boolean insert(FreightRegion freightRegion) {
        return  baseMapper.insert(freightRegion) > 0;
    }

    @Override
    public Boolean update(FreightRegion freightRegion) {
        return baseMapper.updateById(freightRegion) > 0;
    }

    @Override
    public Boolean delete(List<Integer> ids) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }

}