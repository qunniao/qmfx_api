package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.QuestionQueryDTO;
import com.zhancheng.core.entity.Question;
import com.zhancheng.core.vo.QuestionListItemVO;
import com.zhancheng.core.vo.QuestionListVO;
import com.zhancheng.core.vo.QuestionVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 代理等级
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface QuestionMapper extends BaseMapper<Question> {

    IPage<QuestionVO> queryPage(Page<Question> page, QuestionQueryDTO queryDto);

    QuestionVO queryInfo(Integer id);

    List<QuestionListItemVO> queryList(QuestionQueryDTO queryDto);
}
