package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.UserBandCardDTO;
import com.zhancheng.core.entity.UserAddress;
import com.zhancheng.core.entity.UserBandCard;

import java.util.List;

/**
 * 用户地址
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-25 16:51:16
 */
public interface UserBandCardService extends IService<UserBandCard> {

    /**
     * 分页查询用户地址列表
     * @param pageDto 分页参数
     * @return IPage<UserAddress>
     */
    IPage<UserBandCard> selectPage(PageDto<UserBandCard> pageDto);

    /**
     * 查询用户地址详情
     * @param id 主键Id
     * @return
     */
    UserBandCard info(Integer id);

    /**
     * 添加用户地址
     * @param userAddress 用户地址数据
     * @return Boolean
     */
    Boolean insert(UserBandCardDTO userAddress);

    /**
     * 修改用户地址
     * @param userAddress 用户地址数据
     * @return Boolean
     */
    Boolean update(UserBandCardDTO userAddress);

    /**
     * 批量删除用户地址
     * @param ids 用户地址id集合
     * @return Boolean
     */
    Boolean delete(List<Integer> ids);

}

