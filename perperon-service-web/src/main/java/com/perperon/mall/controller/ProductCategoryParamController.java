package com.perperon.mall.controller;

import com.perperon.mall.pojo.ProductCategoryParam;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.ProductCategoryParamService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perperon
 * @date 2024-04-09
 * @apiNote 商品分类参数模块
 */
@RestController
@RequestMapping("/productcategoryparam")
@Api(tags = "商品分类参数模块")
public class ProductCategoryParamController extends BaseController<ProductCategoryParam>{

    @Autowired
    private ProductCategoryParamService productCategoryParamService;


    @Override
    public BaseService<ProductCategoryParam> getService() {
        return productCategoryParamService;
    }

}
