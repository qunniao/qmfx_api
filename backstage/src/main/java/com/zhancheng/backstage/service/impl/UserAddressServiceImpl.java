package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.UserAddressService;
import com.zhancheng.core.dao.UserAddressMapper;
import com.zhancheng.core.entity.UserAddress;
import org.springframework.stereotype.Service;

/**
 * 收货地址
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

}