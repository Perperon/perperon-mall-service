package com.perperon.mall.mapper;

import com.perperon.mall.pojo.DictionaryType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-07-18
 * @apiNote
 */
public interface DictionaryTypeMapper extends Mapper<DictionaryType> {

    List<DictionaryType> listByPage(Map<String,Object> params);
}
