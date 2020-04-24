package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.ProductQueryDTO;
import com.zhancheng.core.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.ProductListVO;
import com.zhancheng.core.vo.ProductVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 查询产品列表
     * @param page
     * @param productQueryDto
     * @return
     */
    IPage<ProductListVO> queryList(Page page, @Param("query") ProductQueryDTO productQueryDto);

    /**
     * 查询产品信息
     * @param pid 产品id
     * @return ProductVO
     */
    ProductVO queryInfo(Integer pid);

    /**
     * 查询产品信息
     * @return ProductVO
     */
    List<ProductListVO> queryRecommend();

    /**
     * 根据运费id统计商品数量
     * @param fid
     * @return
     */
    Integer countFreightNum(@Param("fid") Integer fid);
}
