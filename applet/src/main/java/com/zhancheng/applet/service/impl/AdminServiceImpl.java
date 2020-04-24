package com.zhancheng.applet.service.impl;

import com.zhancheng.applet.service.AdminService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.AdminMapper;
import com.zhancheng.core.entity.Admin;

/**
 * 管理员
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}