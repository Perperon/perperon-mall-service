package com.perperon.mall.controller;

import com.perperon.mall.pojo.ProductCategory;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.ProductCategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perperon
 * @date 2024-04-09
 * @apiNote 菜单管理模块
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

}
