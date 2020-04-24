package com.zhancheng.backstage.service.impl;

import com.zhancheng.backstage.service.ProductImageService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.core.dao.ProductImageMapper;
import com.zhancheng.core.entity.ProductImage;

/**
 * 商品轮播图
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */

@Service
public class ProductImageServiceImpl extends ServiceImpl<ProductImageMapper, ProductImage> implements ProductImageService {

}