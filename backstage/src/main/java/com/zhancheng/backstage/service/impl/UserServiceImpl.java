package com.zhancheng.backstage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.UserService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.UserMapper;
import com.zhancheng.core.dao.WalletMapper;
import com.zhancheng.core.dto.UserDTO;
import com.zhancheng.core.entity.User;
import com.zhancheng.core.vo.UserAgentListVO;
import com.zhancheng.core.vo.UserListVO;
import com.zhancheng.core.vo.UserVO;
import com.zhancheng.core.vo.WalletVO;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private WalletMapper walletMapper;

    @Override
    public IPage<UserListVO> queryPage(PageDto pageDto) {

        IPage<UserListVO> userListVoPage = baseMapper.queryPage(pageDto.getPage());

        List<UserListVO> recordList = userListVoPage.getRecords();

        if (ObjectUtil.isNotNull(recordList)) {
            for (UserListVO record : recordList) {
                //Integer id = record.getId();
               // WalletVO walletVo = walletMapper.queryInfo(id);
                //record.setCommissionBalance(walletVo.getCommissionBalance());
            }
        }
        return userListVoPage;
    }

    @Override
    public UserVO queryInfo(Integer userId) {
        return baseMapper.queryInfo(userId);
    }

    @Override
    public IPage<UserAgentListVO> queryListByAgentInfo(PageDto<User> pageDto) {
        IPage<UserAgentListVO> userListVoPage = baseMapper.queryListByAgentInfo(pageDto.getPage());
        return userListVoPage;
    }

    @Override
    public Boolean update(UserDTO userDTO) {
        log.info("update id:"+userDTO.getId());
        UserVO userVO = baseMapper.queryInfo(userDTO.getId());
        if (ObjectUtil.isNull(userVO)) {
            throw new MyException(CodeMsg.USER_NOT_EXISTED);
        }
        User user = new User();
        BeanUtil.copyProperties(userVO, user);
        user.setAgentLevel(userDTO.getAgentLevel());
        user.updateById();
        return true;
    }
}