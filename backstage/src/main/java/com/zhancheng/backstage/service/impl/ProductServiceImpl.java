package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.ProductImageService;
import com.zhancheng.backstage.service.ProductService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.dao.ProductTypeMapper;
import com.zhancheng.core.dto.ProductQueryDTO;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.ProductImage;
import com.zhancheng.core.vo.ProductListVO;
import com.zhancheng.core.vo.ProductVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 产品spu表
 *
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductImageService productImageService;

    @Resource
    private ProductTypeMapper productTypeMapper;

    @Override
    public IPage<ProductListVO> queryPage(PageDto<Product> pageDto, ProductQueryDTO productQueryDto) {
        return baseMapper.queryList(pageDto.getPage(), productQueryDto);
    }

    @Override
    public ProductVO queryInfo(Integer pid) {

        ProductVO productVo = baseMapper.queryInfo(pid);

        if (ObjectUtil.isNull(productVo)) {
            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }

        // 查询并添加 产品轮播图
        List<ProductImage> productImageList = productImageService.list(new QueryWrapper<ProductImage>()
                .eq("product_id", pid).orderByDesc("sort"));

        productVo.setProductImageList(productImageList);

        return productVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(Product product) {

        isExist(product.getProductNumber(), null);

        product.insert();
        Integer pid = product.getPid();
        List<ProductImage> productImageList = product.getProductImageList();

        // 添加轮播图
        if (ObjectUtil.isNotEmpty(productImageList)) {
            for (ProductImage productImage : productImageList) {
                productImage.setProductId(pid);
            }
            productImageService.saveBatch(productImageList);
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(Product product) {

        isExist(product.getProductNumber(), product.getPid());

        product.updateById();

        List<ProductImage> productImageList = product.getProductImageList();

        // 添加轮播图
        if (ObjectUtil.isNotEmpty(productImageList)) {
            productImageService.remove(new QueryWrapper<ProductImage>().eq("product_id", product.getPid()));
            for (ProductImage productImage : productImageList) {
                productImage.setProductId(product.getPid());
            }
            productImageService.saveBatch(productImageList);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean delete(List<Integer> pids) {
        return baseMapper.deleteBatchIds(pids) > 0;
    }

    private void isExist(String number, Integer pid) {

        Product productInfo = baseMapper.selectOne(new QueryWrapper<Product>()
                .eq("product_number", number)
                .eq("is_deleted", 0));

        if (ObjectUtil.isNotNull(productInfo)) {

            // 判断是不是修改 并且 pid 是不是相同。
            if (ObjectUtil.isNotNull(pid) && ObjectUtil.notEqual(productInfo.getPid(), pid)) {
                throw new MyException(CodeMsg.PRODUCT_NUMBER_EXIST);
            }

            // 判断是不是添加
            if (ObjectUtil.isNull(pid)) {
                throw new MyException(CodeMsg.PRODUCT_NUMBER_EXIST);
            }
        }
    }
}