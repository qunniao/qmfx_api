package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.ProductTypeService;
import com.zhancheng.core.dao.ProductTypeMapper;
import com.zhancheng.core.entity.ProductType;
import org.springframework.stereotype.Service;


/**
 * 产品类目
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */

@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {

}