package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.mapper.ProductParamMapper;
import com.perperon.mall.pojo.ProductParam;
import com.perperon.mall.service.ProductParamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-08-05
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class ProductParamServiceImpl implements ProductParamService {

    @Resource
    private ProductParamMapper productParamMapper;

    @Override
    public Mapper<ProductParam> getMapper() {
        return productParamMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return new PageInfo(productParamMapper.listProductParamByPage(params));
    }
}
