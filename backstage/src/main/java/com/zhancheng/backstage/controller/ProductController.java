package com.zhancheng.backstage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.ProductService;
import com.zhancheng.core.commom.PageDto;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.dto.ProductQueryDTO;
import com.zhancheng.core.entity.Product;
import com.zhancheng.core.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Api(tags = "商品")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Resource
    private ProductService productService;

//    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询商品列表")
    @GetMapping("/page")
    public R<IPage<Product>> queryPage(PageDto<Product> pageDto, ProductQueryDTO productQueryDto){

        return R.ok(productService.queryPage(pageDto, productQueryDto));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询商品详情")
    @ApiImplicitParam(name = "pid", value = "主键id")
    @GetMapping("/info/{pid}")
    public R<ProductVO> queryInfo(@PathVariable("pid") Integer pid){

        return R.ok(productService.queryInfo(pid));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加商品")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "pid", value = "商品id"),
                        @ApiImplicitParam(name = "typeId", value = "商品类型id"),
                        @ApiImplicitParam(name = "productNumber", value = "商品货号"),
                        @ApiImplicitParam(name = "title", value = "商品名称"),
                        @ApiImplicitParam(name = "cover", value = "商品主图"),
                        @ApiImplicitParam(name = "video", value = "商品介绍视频"),
                        @ApiImplicitParam(name = "productIntro", value = "商品简介"),
                        @ApiImplicitParam(name = "marketPrice", value = "市场价"),
                        @ApiImplicitParam(name = "price", value = "售价"),
                        @ApiImplicitParam(name = "stockPrice", value = "进货价"),
                        @ApiImplicitParam(name = "sales", value = "基础销量：系统虚拟的销量基数"),
                        @ApiImplicitParam(name = "flowSales", value = "流水销量，提交订单后增加"),
                        @ApiImplicitParam(name = "realSales", value = "实际销量，确认收货后增加，退换货减少"),
                        @ApiImplicitParam(name = "totalStock", value = "库存数量，发货后减少"),
                        @ApiImplicitParam(name = "detailMobile", value = "手机端详情"),
                        @ApiImplicitParam(name = "isRecommend", value = "推荐 1.是0.否"),
                        @ApiImplicitParam(name = "retailFreightId", value = "零售运费模板id")
            })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody Product product){

        return R.ok(productService.insert(product));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改商品")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "pid", value = "商品id"),
                        @ApiImplicitParam(name = "typeId", value = "商品类型id"),
                        @ApiImplicitParam(name = "productNumber", value = "商品货号"),
                        @ApiImplicitParam(name = "title", value = "商品名称"),
                        @ApiImplicitParam(name = "cover", value = "商品主图"),
                        @ApiImplicitParam(name = "video", value = "商品介绍视频"),
                        @ApiImplicitParam(name = "productIntro", value = "商品简介"),
                        @ApiImplicitParam(name = "marketPrice", value = "市场价"),
                        @ApiImplicitParam(name = "price", value = "售价"),
                        @ApiImplicitParam(name = "stockPrice", value = "进货价"),
                        @ApiImplicitParam(name = "sales", value = "基础销量：系统虚拟的销量基数"),
                        @ApiImplicitParam(name = "flowSales", value = "流水销量，提交订单后增加"),
                        @ApiImplicitParam(name = "realSales", value = "实际销量，确认收货后增加，退换货减少"),
                        @ApiImplicitParam(name = "totalStock", value = "库存数量，发货后减少"),
                        @ApiImplicitParam(name = "detailMobile", value = "手机端详情"),
                        @ApiImplicitParam(name = "isRecommend", value = "推荐 1.是0.否"),
                        @ApiImplicitParam(name = "retailFreightId", value = "零售运费模板id")
            })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody Product product){

        return R.ok(productService.update(product));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> pids){

        return R.ok(productService.delete(pids));
    }

}
