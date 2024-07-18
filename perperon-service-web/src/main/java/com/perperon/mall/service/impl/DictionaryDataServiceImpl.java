package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.mapper.DictionaryDataMapper;
import com.perperon.mall.pojo.DictionaryData;
import com.perperon.mall.service.DictionaryDataService;
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
public class DictionaryDataServiceImpl implements DictionaryDataService {
    @Resource
    private DictionaryDataMapper dictionaryDataMapper;

    @Override
    public Mapper<DictionaryData> getMapper() {
        return dictionaryDataMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return new PageInfo(dictionaryDataMapper.listByPage(params));
    }

    @Override
    public CommonResult<DictionaryData> getDictionaryByCode(String code) {
        return CommonResult.success(dictionaryDataMapper.getDictionaryByCode(code), "查询成功");
    }
}
