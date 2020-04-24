package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.UserAddress;
import com.zhancheng.core.entity.UserBandCard;
import com.zhancheng.core.vo.UserBandCardVO;
import org.springframework.stereotype.Repository;

/**
 * 银行卡
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface UserBandCardMapper extends BaseMapper<UserBandCard> {

    UserBandCard queryInfoByCardNumber(String cardNumber, Integer userId);
}
