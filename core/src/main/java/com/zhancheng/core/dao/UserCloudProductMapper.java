package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.OrderProduct;
import com.zhancheng.core.entity.UserCloudProduct;
import com.zhancheng.core.vo.OrderProductVO;
import com.zhancheng.core.vo.UserCloudProductVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单商品
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface UserCloudProductMapper extends BaseMapper<UserCloudProduct> {

    /**
     * 根据订单id查询订单商品
     *
     * @return order product
     */
    List<UserCloudProductVO> queryList(Integer userId);

    UserCloudProductVO queryInfoByProductIdAndUserId(Integer userId, Integer pid);
}
