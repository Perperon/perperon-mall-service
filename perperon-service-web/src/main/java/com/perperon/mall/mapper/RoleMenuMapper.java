package com.perperon.mall.mapper;

import com.perperon.mall.pojo.RoleMenu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-06-18
 * @apiNote
 */
public interface RoleMenuMapper extends Mapper<RoleMenu> {

   List<RoleMenu> listByPage(Map<String, Object> params);

   List<RoleMenu> listByRoleId(@Param("roleId") String roleId);
}
