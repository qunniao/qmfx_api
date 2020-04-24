package com.zhancheng.backstage.service;

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
     * 分页查询列表
     * @param pageDto 分页参数
     * @return IPage<Freight>
     */
    IPage<FreightListVo> selectPage(PageDto<Freight> pageDto);

    /**
     * 查询详情
     * @param id 主键Id
     * @return
     */
    Freight info(Integer id);

    /**
     * 添加
     * @param freight 数据
     * @return Boolean
     */
    Boolean insert(Freight freight);

    /**
     * 修改
     * @param freight 数据
     * @return Boolean
     */
    Boolean update(Freight freight);

    /**
     * 批量删除
     * @param id 模板id
     * @return Boolean
     */
    Boolean delete(Integer id);
}

