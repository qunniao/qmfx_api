package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.CartListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Repository
public interface CartMapper extends BaseMapper<Cart> {

    /**
     * 分页查询购物车列表
     *
     * @param page
     * @param userId
     * @return
     */
    IPage<CartListVO> queryPage(Page page, @Param("userId") Integer userId);

    /**
     * 结算购物车
     * @param cIds
     * @return
     */
    List<CartListVO> queryInfo(@Param("cIds") List<Integer> cIds);


}
