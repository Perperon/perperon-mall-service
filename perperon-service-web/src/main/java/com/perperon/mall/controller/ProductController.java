package com.perperon.mall.controller;

import com.perperon.mall.annotation.PreAuthorize;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.pojo.Product;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dupengcheng
 * @date 2024-07-24
 * @apiNote
 */

@RestController
@RequestMapping("/product")
@Api(tags = "商品列表模块")
public class ProductController extends BaseController<Product>{
    @Autowired
    private ProductService productService;

    @Override
    public BaseService<Product> getService() {
        return productService;
    }

    @PutMapping("/updateStatus")
    @ApiOperation("更新状态")
    @PreAuthorize("@ps.hasPerm('product:update')")
    public CommonResult<Product> updateStatus(@RequestBody Product obj){
        return productService.updateStatus(obj);
    }
}
