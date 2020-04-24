package com.zhancheng.applet.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.ProductService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.dto.ProductQueryDTO;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.vo.ProductListVO;
import com.zhancheng.core.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * 产品spu表
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:28
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Resource
    private ProductService productService;

    @ApiOperation(value = "分页查询产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "类型id"),
            @ApiImplicitParam(name = "search", value = "搜索框参数"),
            @ApiImplicitParam(name = "sortType", value = "排序类型:1.价格升序;2.价格降序;3.销量升序;4.销量升序"),
    })
    @GetMapping("/list")
    public R<IPage<ProductListVO>> list(PageDto<Product> pageDto, ProductQueryDTO productQueryDto) {

        return R.ok(productService.queryList(pageDto, productQueryDto));
    }

    @ApiOperation(value = "查询商品详情")
    @GetMapping("/info")
    public R<ProductVO> info(Integer pid) {

        return R.ok(productService.info(pid));
    }

    @ApiOperation(value = "查询商品二维码图片")
    @GetMapping("/getProductCodeImage")
    public byte[] getProductCodeImage(Integer pid, Integer userId, HttpServletResponse response) throws IOException {
        byte[] result = productService.getProductCodeImage(pid,userId);
//输出图片到页面
        /*
        PrintWriter out = response.getWriter();
        InputStream is = new ByteArrayInputStream(result);
        int a = is.read();
         while (a != -1) {
            out.print((char) a);
            a = is.read();
         }
         out.flush();
         out.close();

         */
         return result;
        //return productService.getProductCodeImage(pid,userId);
    }

    @ApiOperation(value = "查询今日推荐")
    @GetMapping("/recommend")
    public R<List<ProductListVO>> recommend() {

        return R.ok(productService.queryRecommend());
    }



}
