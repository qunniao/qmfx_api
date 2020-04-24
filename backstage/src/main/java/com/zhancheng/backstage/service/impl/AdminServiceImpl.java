package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.AdminService;
import com.zhancheng.core.dao.AdminMapper;
import com.zhancheng.core.entity.Admin;
import org.springframework.stereotype.Service;

/**
 * @author BianShuHeng
 * @decription
 * @project AdminServiceImpl
 * @date 2019/11/20 17:54
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
