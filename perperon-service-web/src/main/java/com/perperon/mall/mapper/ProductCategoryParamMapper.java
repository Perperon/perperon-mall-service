package com.perperon.mall.mapper;

import com.perperon.mall.pojo.ProductCategoryParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-07-18
 * @apiNote
 */
public interface ProductCategoryParamMapper extends Mapper<ProductCategoryParam> {

    List<ProductCategoryParam> listByPage(Map<String, Object> map);
}
