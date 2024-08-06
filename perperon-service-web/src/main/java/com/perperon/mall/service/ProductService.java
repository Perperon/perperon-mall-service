package com.perperon.mall.service;

import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.pojo.Product;

/**
 * @author dupengcheng
 * @date 2024-07-24
 * @apiNote
 */
public interface ProductService extends BaseService<Product>{

    CommonResult<Product> updateStatus(Product product);
}
