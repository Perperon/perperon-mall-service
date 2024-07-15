package com.perperon.mall.mapper;

import com.perperon.mall.dto.MenuDto;
import com.perperon.mall.pojo.ProductCategory;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-07-15
 * @apiNote
 */
public interface ProductCategoryMapper extends Mapper<ProductCategory> {

    List<MenuDto> listByPage(Map<String, Object> map);
}
