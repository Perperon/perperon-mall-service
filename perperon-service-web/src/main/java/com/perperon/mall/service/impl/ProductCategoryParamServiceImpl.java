package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.mapper.ProductCategoryParamMapper;
import com.perperon.mall.pojo.ProductCategoryParam;
import com.perperon.mall.service.ProductCategoryParamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author perperon
 * @date 2024/4/15
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class ProductCategoryParamServiceImpl implements ProductCategoryParamService {
    @Resource
    private ProductCategoryParamMapper productCategoryParamMapper;

    @Override
    public Mapper<ProductCategoryParam> getMapper() {
        return productCategoryParamMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        List<ProductCategoryParam> productCategoryList = productCategoryParamMapper.listByPage(params);
        return new PageInfo(productCategoryList);
    }
}
