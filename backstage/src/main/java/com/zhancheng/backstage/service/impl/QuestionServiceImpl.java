package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zhancheng.backstage.service.QuestionService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;

import com.zhancheng.core.dao.QuestionMapper;
import com.zhancheng.core.dto.QuestionQueryDTO;
import com.zhancheng.core.entity.Question;
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
    private QuestionMapper quesionMapper;

    @Override
    public IPage<QuestionVO> queryPage(PageDto<Question> pageDto, QuestionQueryDTO queryDto) {
        return baseMapper.queryPage(pageDto.getPage(),queryDto);
    }


    @Override
    public QuestionVO queryInfo(Integer id) {

        QuestionVO item = baseMapper.queryInfo(id);

        if (ObjectUtil.isNull(item)) {
            throw new MyException(CodeMsg.NULL_POINTER_EXCEPTION);
        }

        return item;

    }

    @Override
    public Boolean update(Question modal) {
        log.info("修改 id:"+modal.getId());
        QuestionVO item = baseMapper.queryInfo(modal.getId());
        if (ObjectUtil.isNull(item)) {
            throw new MyException(CodeMsg.NULL_POINTER_EXCEPTION);
        }
        modal.updateById();
        return true;
    }

    @Override
    public Boolean delete(List<Integer> ids) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}