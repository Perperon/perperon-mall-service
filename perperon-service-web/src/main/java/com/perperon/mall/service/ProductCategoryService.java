package com.perperon.mall.service;

import com.perperon.mall.dto.ProductCategoryDto;
import com.perperon.mall.pojo.ProductCategory;

import java.util.List;
import java.util.Map;

/**
 * @author perperon
 * @date 2024-04-15
 * @apiNote
 */
public interface ProductCategoryService extends BaseService<ProductCategory> {

    List<ProductCategoryDto> listByParentPage(Map<String,Object> params);
}
