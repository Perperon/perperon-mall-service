package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.mapper.DictionaryTypeMapper;
import com.perperon.mall.pojo.DictionaryType;
import com.perperon.mall.service.DictionaryTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-07-18
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class DictionaryTypeServiceImpl implements DictionaryTypeService {
    @Resource
    private DictionaryTypeMapper dictionaryTypeMapper;
    @Override
    public Mapper<DictionaryType> getMapper() {
        return dictionaryTypeMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return new PageInfo(dictionaryTypeMapper.listByPage(params));
    }
}
