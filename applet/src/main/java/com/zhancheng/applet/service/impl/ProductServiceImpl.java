package com.zhancheng.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.ProductService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.exception.MyException;
import com.zhancheng.core.constant.CodeMsg;
import com.zhancheng.core.dao.ProductImageMapper;
import com.zhancheng.core.dao.ProductMapper;
import com.zhancheng.core.dto.ProductQueryDTO;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.entity.ProductImage;
import com.zhancheng.core.util.HttpUtil;
import com.zhancheng.core.vo.ProductListVO;
import com.zhancheng.core.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project ProductServiceImpl
 * @date 2019/10/25 10:07
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    private static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductImageMapper productImageMapper;

    @Override
    public IPage<ProductListVO> queryList(PageDto pageDto, ProductQueryDTO productQueryDto) {
        return baseMapper.queryList(pageDto.getPage(), productQueryDto);
    }

    @Override
    public ProductVO info(Integer pid) {

        ProductVO productVo = productMapper.queryInfo(pid);

        if (ObjectUtil.isNull(productVo)) {
            throw new MyException(CodeMsg.PRODUCT_NOT_EXISTED);
        }

        // 查询并添加 产品轮播图
        List<ProductImage> productImageList = productImageMapper.selectList(new QueryWrapper<ProductImage>()
                .eq("product_id", pid).orderByDesc("sort"));

        productVo.setProductImageList(productImageList);
        return productVo;
    }

    @Override
    public List<ProductListVO> queryRecommend() {

        return baseMapper.queryRecommend();
    }

    @Override
    public byte[] getProductCodeImage(Integer pid, Integer userId) {
        String appid = "wxcb0646a754eb05bc";
        String sec = "934632a0fff99372406fde67eca26ddc";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + sec;
        String res = HttpUtil.doGet(url);
        log.info("获取access_token:"+res);
        JSONObject resJson = new JSONObject(res);
        String access_token = resJson.get("access_token").toString();
        log.info("access_token:"+access_token);
        //获取二维码图片
        String url3 = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token;
        String param3 = "{\"scene\":"+"\"pid=" + pid + "&shareId=" + userId+"\","+"\"path\":\"pagesA/details/details\"}";
        log.info(param3);
        //组装参数
        Map<String, Object> paraMap = new HashMap();
        //二维码携带参数 不超过32位
        paraMap.put("scene", "pid=" + pid + "&shareId=" + userId);
        //二维码跳转页面
        paraMap.put("path", "pagesA/details/details");
        byte[] res3 = HttpUtil.doImgPost(url3,paraMap);
        log.info("获取二维码图片:"+res3);

        return res3;
    }
}
