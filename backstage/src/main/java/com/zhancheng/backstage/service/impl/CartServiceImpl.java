package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.CartService;
import com.zhancheng.core.dao.CartMapper;
import com.zhancheng.core.entity.Cart;
import org.springframework.stereotype.Service;

/**
 * 购物车
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}