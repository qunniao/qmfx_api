package com.zhancheng.applet.service.impl;

import com.zhancheng.applet.service.UserService;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.entity.User;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 用户
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;

    @Override
    public User lockInviter(Integer id, Integer inviterId) {
        log.info("lockInviter id:"+id+" inviterId:"+inviterId);
        User user = userMapper.selectById(id);
        if(user==null) return null;
        if(user.getInviterId()!=0){
            //已经有邀请人了，不再设置
            return null;
        }
        user.setInviterId(inviterId);
        user.updateById();

        return user;
    }
}