package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.RewardVO;
import com.zhancheng.core.vo.UserAgentListVO;
import com.zhancheng.core.vo.UserListVO;
import com.zhancheng.core.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户列表
     * @param page 分页数据
     * @return UserListVO
     */
    IPage<UserListVO> queryPage(Page page);

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     * @return UserVO
     */
    UserVO queryInfo(Integer userId);

    /**
     * 查询用户信息
     *
     * @param id 用户id
     * @return UserVO
     */
    UserVO queryInfoById(Integer id);


    /**
     * 查询下级用户
     * @param page 分页信息
     * @param inviterId 上级id
     * @return RewardVO
     */
    IPage<RewardVO> queryLower(Page page, Integer inviterId);

    /**
     * 查询用户最近的销量
     * @param userId 上级id
     * @return RewardVO
     */
    Integer queryLowerRecentlySales(Integer userId);

    void updateInviterId(UserVO userVO);

    IPage<UserAgentListVO> queryListByAgentInfo(Page<User> page);
}
