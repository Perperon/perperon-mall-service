package com.perperon.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.perperon.mall.dto.ProductCategoryDto;
import com.perperon.mall.mapper.ProductCategoryMapper;
import com.perperon.mall.pojo.ProductCategory;
import com.perperon.mall.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author perperon
 * @date 2024/4/15
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public Mapper<ProductCategory> getMapper() {
        return productCategoryMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        List<ProductCategoryDto> productCategoryList = productCategoryMapper.listByPage(params);
        //排序
        productCategoryList.sort(Comparator.nullsLast(Comparator.comparingInt(ProductCategoryDto::getLevel)));
        List<ProductCategoryDto> result = productCategoryList.stream()
                .filter(dto -> StrUtil.isBlank(dto.getPId()))
                .map(dto -> covertMenuNode(dto, productCategoryList))
                .collect(Collectors.toList());
        return new PageInfo(result);
    }

    /**
     * 将Menu转化为MenuDto并设置children属性
     */
    private ProductCategoryDto covertMenuNode(ProductCategoryDto productCategoryDto, List<ProductCategoryDto> productCategoryDtoList) {
        ProductCategoryDto node = new ProductCategoryDto();
        BeanUtils.copyProperties(productCategoryDto, node);
        List<ProductCategoryDto> children = productCategoryDtoList.stream()
                .filter(subDto -> productCategoryDto.getId().equals(subDto.getPId()))
                .map(subDto -> covertMenuNode(subDto, productCategoryDtoList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    @Override
    public List<ProductCategoryDto> listByParentPage(Map<String, Object> params) {
        List<ProductCategoryDto> productCategoryList = productCategoryMapper.listByParentPage(params);
        List<ProductCategoryDto> result = productCategoryList.stream()
                .filter(dto -> StrUtil.isBlank(dto.getPId()))
                .map(dto -> covertMenuNode(dto, productCategoryList))
                .collect(Collectors.toList());
        return result;
    }
}
