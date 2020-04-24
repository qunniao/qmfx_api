package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.VisitorService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dao.OrderMapper;
import com.zhancheng.core.entity.Order;
import com.zhancheng.core.vo.VisitorListVO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.VisitorMapper;
import com.zhancheng.core.entity.Visitor;

import javax.annotation.Resource;
import java.util.List;

/**
 * 访客记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-23 11:31:19
 */

@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public IPage<VisitorListVO> queryPage(PageDto pageDto) {

        IPage<VisitorListVO> visitorListVoPage = baseMapper.queryPage(pageDto.getPage());

        List<VisitorListVO> recordList = visitorListVoPage.getRecords();

        if (ObjectUtil.isNotEmpty(recordList)) {
            for (VisitorListVO visitorListVo : recordList) {

                Integer userId = visitorListVo.getUserId();
                Integer orderNum = orderMapper.selectCount(new QueryWrapper<Order>().eq("user_id", userId)
                        .eq("is_deleted", 0));

                visitorListVo.setOrderNum(orderNum);
            }
        }
        return visitorListVoPage;
    }
}