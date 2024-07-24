package com.perperon.mall.mapper;

import com.perperon.mall.pojo.Product;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-07-24
 * @apiNote
 */
public interface ProductMapper extends Mapper<Product> {

    List<Product> listByPage(Map<String,Object> params);
}
