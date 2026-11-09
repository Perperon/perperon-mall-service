package com.perperon.mall.mapper;

import com.perperon.mall.pojo.Logs;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-07-15
 * @apiNote
 */
public interface LogsMapper extends Mapper<Logs> {

    List<Logs> listLogsByPage(Map<String,Object> params);

}
