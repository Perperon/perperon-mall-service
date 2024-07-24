package com.perperon.mall.service;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.mapper.ProductMapper;
import com.perperon.mall.pojo.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-07-24
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{

    @Resource
    private ProductMapper productMapper;

    @Override
    public Mapper<Product> getMapper() {
        return productMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return new PageInfo(productMapper.listByPage(params));
    }
}
