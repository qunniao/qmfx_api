package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.OrderCartDTO;
import com.zhancheng.core.entity.Cart;
import com.zhancheng.core.vo.CartConfirmListVO;
import com.zhancheng.core.vo.CartListVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * 购物车
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
public interface CartService extends IService<Cart> {

    /**
     * 分页查询购物车
     *
     * @param pageDt
     * @param userId
     * @return page
     */
    IPage<CartListVO> queryPage(PageDto<Cart> pageDt, Integer userId);

    /**
     * 提交购物车
     *
     * @param orderCartDto
     * @return string
     */
    String submitCart(OrderCartDTO orderCartDto);

    /**
     * 添加或修改购物车
     *
     * @param cart
     * @return boolean
     */
    Boolean insertOrUpdate(@RequestBody Cart cart);

    /**
     * 添加或减少商品数量
     * @param cid 购物车id
     * @param num 商品数量
     * @return
     */
    Boolean changeProductNum(Integer cid, Integer num);

    /**
     * 查询购物车信息
     *
     * @param cIds
     * @return list
     */
    Map<String, Object> queryInfo(List<Integer> cIds);

    Map<String,Object> queryCartDiscount(OrderCartDTO orderCartDto);

    Map<String,Object> queryProductOrderPrice(Integer userId, Integer productId, Integer productNum);
}

