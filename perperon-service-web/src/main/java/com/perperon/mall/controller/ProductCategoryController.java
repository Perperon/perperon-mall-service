package com.perperon.mall.controller;

import com.perperon.mall.common.response.CommonPage;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.dto.ProductCategoryDto;
import com.perperon.mall.pojo.ProductCategory;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author perperon
 * @date 2024-04-09
 * @apiNote 商品分类模块
 */
@RestController
@RequestMapping("/productcategory")
@Api(tags = "商品分类模块")
public class ProductCategoryController extends BaseController<ProductCategory>{

    @Autowired
    private ProductCategoryService productCategoryService;


    @Override
    public BaseService<ProductCategory> getService() {
        return productCategoryService;
    }

    @GetMapping(value = "/listByParentPage")
    @ApiOperation("查询父级列表")
    @ResponseBody
    public CommonResult<CommonPage<ProductCategoryDto>> listByParentPage(@RequestParam Map<String,Object> params){
        return CommonResult.success(CommonPage.restPage(productCategoryService.listByParentPage(params)));
    }
}
