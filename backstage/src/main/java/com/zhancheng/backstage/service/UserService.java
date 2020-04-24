package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.UserDTO;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.vo.UserAgentListVO;
import com.zhancheng.core.vo.UserListVO;
import com.zhancheng.core.vo.UserVO;

/**
 * 用户
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户列表
     * @param pageDto 分页信息
     * @return UserListVO
     */
    IPage<UserListVO> queryPage(PageDto pageDto);

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     * @return UserVO
     */
    UserVO queryInfo(Integer userId);


    IPage<UserAgentListVO> queryListByAgentInfo(PageDto<User> pageDto);

    Boolean update(UserDTO user);
}

