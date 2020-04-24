package com.zhancheng.backstage.controller;

import com.zhancheng.backstage.service.ProductTypeService;
import com.zhancheng.core.config.security.Verify;
import com.zhancheng.core.constant.R;
import com.zhancheng.core.constant.UserIdentity;
import com.zhancheng.core.entity.ProductType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 产品类目
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-17 14:19:27
 */
@Api(tags = "产品类目")
@RestController
@RequestMapping("/productTypes")
@Verify(role = UserIdentity.ADMIN)
public class ProductTypeController {

    @Resource
    private ProductTypeService productTypeService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询产品类目列表")
    @GetMapping("/list")
    public R<List<Map<String, Object>>> list(){

        return R.ok(productTypeService.list());
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询商品类型详情")
    @ApiImplicitParam(name = "id", value = "类型id")
    @GetMapping("/info/{id}")
    public R<ProductType> queryInfo(@PathVariable("id") Integer id){

        return R.ok(productTypeService.getById(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加商品类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "类型名"),
            @ApiImplicitParam(name = "desc", value = "描述")
    })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody ProductType productType){

        return R.ok(productTypeService.save(productType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改商品类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", value = "商品类型id", required = true),
            @ApiImplicitParam(name = "title", value = "类型名"),
            @ApiImplicitParam(name = "desc", value = "描述")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody ProductType productType){

        return R.ok(productTypeService.updateById(productType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除商品类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类型id"),
    })
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Integer id){

        return R.ok(productTypeService.removeById(id));
    }


}
