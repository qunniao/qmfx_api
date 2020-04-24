package com.zhancheng.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.QuestionService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.QuestionMapper;
import com.zhancheng.core.dao.QuestionTypeMapper;
import com.zhancheng.core.dto.QuestionQueryDTO;
import com.zhancheng.core.entity.Question;
import com.zhancheng.core.vo.QuestionListItemVO;
import com.zhancheng.core.vo.QuestionListVO;
import com.zhancheng.core.vo.QuestionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
    private static Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionTypeMapper quesionTypeMapper;

    @Override
    public List<QuestionListVO> queryPage(PageDto<Question> pageDto, QuestionQueryDTO queryDto) {
        List<QuestionListVO> questionListVOList = quesionTypeMapper.queryList();
        for(int i=0;i<questionListVOList.size();i++){
            queryDto.setTypeId(questionListVOList.get(i).getId());
            List<QuestionListItemVO> list = questionMapper.queryList(queryDto);
            log.info("list size:"+list.size());
            questionListVOList.get(i).setList(list);
        }
        return questionListVOList;
    }


    @Override
    public QuestionVO queryInfo(Integer id) {

        QuestionVO item = baseMapper.queryInfo(id);

        if (ObjectUtil.isNull(item)) {
            throw new MyException(CodeMsg.NULL_POINTER_EXCEPTION);
        }

        return item;

    }


}