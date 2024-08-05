package com.perperon.mall.controller;

import com.perperon.mall.pojo.ProductParam;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.ProductParamService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dupengcheng
 * @date 2024-07-24
 * @apiNote
 */

@RestController
@RequestMapping("/productparam")
@Api(tags = "商品列表模块")
public class ProductParamController extends BaseController<ProductParam>{
    @Autowired
    private ProductParamService productParamService;

    @Override
    public BaseService<ProductParam> getService() {
        return productParamService;
    }
}
