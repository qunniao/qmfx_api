package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.entity.Visitor;
import com.zhancheng.core.vo.VisitorListVO;

/**
 * 访客记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-23 11:31:19
 */
public interface VisitorService extends IService<Visitor> {

    /**
     * 分页查询访客记录
     * @param pageDto 分页数据
     * @return
     */
    IPage<VisitorListVO> queryPage(PageDto pageDto);
}

