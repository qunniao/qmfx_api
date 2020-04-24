package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.QuestionType;
import com.zhancheng.core.vo.QuestionListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品类型
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface QuestionTypeMapper extends BaseMapper<QuestionType> {

    List<QuestionListVO> queryList();
}
