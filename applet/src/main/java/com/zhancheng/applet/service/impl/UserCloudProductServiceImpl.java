package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.UserCloudProductService;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.dao.UserCloudProductMapper;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.entity.UserCloudProduct;
import com.zhancheng.core.vo.UserCloudProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 用户
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class UserCloudProductServiceImpl extends ServiceImpl<UserCloudProductMapper, UserCloudProduct> implements UserCloudProductService {
    private static Logger log = LoggerFactory.getLogger(UserCloudProductServiceImpl.class);
    @Resource
    private UserCloudProductMapper userCloudProductMapper;


    @Override
    public List<UserCloudProductVO> list(Integer userId) {
        List<UserCloudProductVO> list = userCloudProductMapper.queryList(userId);
        return list;
    }
}