package com.zhancheng.applet.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.VisitorService;
import com.zhancheng.core.dao.VisitorMapper;
import com.zhancheng.core.entity.Visitor;
import org.springframework.stereotype.Service;

/**
 * 访客记录
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-23 11:31:19
 */

@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {

}