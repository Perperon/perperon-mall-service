package com.perperon.mall.mapper;

import com.perperon.mall.dto.RolesDto;
import com.perperon.mall.pojo.Roles;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-06-18
 * @apiNote
 */
public interface RolesMapper extends Mapper<Roles> {

   List<RolesDto> listByPage(Map<String, Object> params);
}
