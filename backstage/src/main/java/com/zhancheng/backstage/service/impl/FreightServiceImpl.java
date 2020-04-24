package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.FreightRegionService;
import com.zhancheng.backstage.service.FreightService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.FreightMapper;
import com.zhancheng.core.dao.FreightRegionMapper;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.entity.Freight;
import com.zhancheng.core.entity.FreightRegion;
import com.zhancheng.core.vo.FreightListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-24 10:18:14
 */

@Service
public class FreightServiceImpl extends ServiceImpl<FreightMapper, Freight> implements FreightService {

    @Autowired
    private FreightRegionService freightRegionService;

    @Autowired
    private FreightRegionMapper freightRegionMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public IPage<FreightListVo> selectPage(PageDto<Freight> pageDto) {

        IPage<FreightListVo> freightPage = baseMapper.queryPage(pageDto.getPage());

        List<FreightListVo> records = freightPage.getRecords();

        if (ObjectUtil.isNull(records)){
            return null;
        }
        for (FreightListVo freightListVo : records) {

            // 统计使用当前模板被使用的数量
            Integer freightNum = productMapper.countFreightNum(freightListVo.getId());
            freightListVo.setFreightNum(freightNum);
        }
        return freightPage;
    }

    @Override
    public Freight info(Integer id) {

        return baseMapper.queryInfo(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(Freight freight) {
        // 保存运费模板并获取模板id
        boolean insert = freight.insert();
        Integer fid = freight.getId();
        Integer valuationWay = freight.getValuationWay();


        // 判断运费模板是否存在
        List<FreightRegion> freightRegionList = freight.getFreightRegionList();
        if (ObjectUtil.isEmpty(freightRegionList)) {
            throw new MyException(CodeMsg.FREIGHT_REGION_NOT_EXISTED);
        }

        for (FreightRegion freightRegion : freightRegionList) {
            freightRegion.setFid(fid);
            freightRegion.setValuationWay(valuationWay);
        }

        // 添加运费地区
        boolean saveBatch = freightRegionService.saveBatch(freightRegionList);

        return insert && saveBatch;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(Freight freight) {

        // 保存运费模板并获取模板id
        boolean update = freight.updateById();
        Integer fid = freight.getId();
        Integer valuationWay = freight.getValuationWay();
        // 判断运费模板是否存在
        List<FreightRegion> freightRegionList = freight.getFreightRegionList();
        if (ObjectUtil.isEmpty(freightRegionList)) {
            throw new MyException(CodeMsg.FREIGHT_REGION_NOT_EXISTED);
        }

        freightRegionMapper.deleteByFid(fid);

        for (FreightRegion freightRegion : freightRegionList) {
            freightRegion.setFid(fid);
            freightRegion.setValuationWay(valuationWay);
        }

        // 添加运费地区
        boolean saveBatch = freightRegionService.saveBatch(freightRegionList);

        return update && saveBatch;
    }

    @Override
    public Boolean delete(Integer id) {
        // 删除模板，删除 区域信息
        int insert = baseMapper.deleteById(id);
        Boolean delete = freightRegionMapper.deleteByFid(id);

        return insert > 0 && delete;
    }

}