package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.Freight;
import com.zhancheng.core.vo.FreightListVo;

/**
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-24 10:18:14
 */
public interface FreightService extends IService<Freight> {


    /**
     * 查询详情
     * @param id 主键Id
     * @return
     */
    Freight info(Integer id);


}

