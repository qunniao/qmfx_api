package com.zhancheng.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.FreightService;
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


    @Override
    public Freight info(Integer id) {

        return baseMapper.queryInfo(id);
    }



}