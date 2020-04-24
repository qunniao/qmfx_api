package com.zhancheng.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.TransferService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.TransferMapper;
import com.zhancheng.core.dto.TransferQueryDTO;
import com.zhancheng.core.entity.Transfer;
import org.springframework.stereotype.Service;

/**
 * @author BianShuHeng
 * @decription
 * @project TransferServiceImpl
 * @date 2019/11/8 15:40
 */
@Service
public class TransferServiceImpl extends ServiceImpl<TransferMapper, Transfer> implements TransferService {

    @Override
    public IPage<Transfer> selectPage(PageDto pageDto, TransferQueryDTO transferQueryDto) {

        Integer type = transferQueryDto.getType();

        return baseMapper.selectPage(pageDto.getPage(), new QueryWrapper<Transfer>()
                .eq("user_id", transferQueryDto.getUserId()).eq(ObjectUtil.isNotNull(type), "type", type));
    }

}
