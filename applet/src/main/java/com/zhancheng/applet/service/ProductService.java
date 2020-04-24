package com.zhancheng.applet.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.dto.ProductQueryDTO;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.vo.ProductListVO;
import com.zhancheng.core.vo.ProductVO;

import java.util.List;

/**
 * 产品spu表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
public interface ProductService extends IService<Product> {


    /**
     * 查询产品详情
     * @param productQueryDto 商品查询信息
     * @param pageDto 分页信息
     * @return
     */
    IPage<ProductListVO> queryList(PageDto pageDto, ProductQueryDTO productQueryDto);

    /**
     * 查询产品spu表详情
     * @param pid 主键Id
     * @return
     */
    ProductVO info(Integer pid);

    /**
     * 查询产品spu表详情
     * @return
     */
    List<ProductListVO> queryRecommend();

    byte[] getProductCodeImage(Integer pid, Integer userId);
}

