package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.QuestionQueryDTO;
import com.zhancheng.core.entity.Question;
import com.zhancheng.core.vo.QuestionListVO;
import com.zhancheng.core.vo.QuestionVO;

import java.util.List;


/**
 * 奖励
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
public interface QuestionService extends IService<Question> {

    List<QuestionListVO> queryPage(PageDto<Question> pageDto, QuestionQueryDTO queryDto);

    QuestionVO queryInfo(Integer id);
}

