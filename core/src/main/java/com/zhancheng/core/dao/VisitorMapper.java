package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.Visitor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.VisitorListVO;
import org.springframework.stereotype.Repository;

/**
 * 访客记录
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-23 11:31:19
 */
@Repository
public interface VisitorMapper extends BaseMapper<Visitor> {

    /**
     * 分页查询访客记录
     * @param page
     * @return
     */
	IPage<VisitorListVO> queryPage(Page page);
}
