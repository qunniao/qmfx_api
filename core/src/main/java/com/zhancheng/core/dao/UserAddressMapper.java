package com.zhancheng.core.dao;

import com.zhancheng.core.entity.UserAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 收货地址
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface UserAddressMapper extends BaseMapper<UserAddress> {
	
}
