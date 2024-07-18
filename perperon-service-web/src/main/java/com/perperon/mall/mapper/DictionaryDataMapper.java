package com.perperon.mall.mapper;

import com.perperon.mall.pojo.DictionaryData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-07-18
 * @apiNote
 */
public interface DictionaryDataMapper extends Mapper<DictionaryData> {

    List<DictionaryData> listByPage(Map<String,Object> params);

    @Select("select id,code,name from pp_dictionary_data where code = #{code}")
    DictionaryData getDictionaryByCode(@Param("code") String code);
}
