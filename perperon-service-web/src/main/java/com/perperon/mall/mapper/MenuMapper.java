package com.perperon.mall.mapper;

import com.perperon.mall.dto.MenuDto;
import com.perperon.mall.pojo.Menu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author perperon
 * @date 2024/4/15
 * @apiNote
 */
public interface MenuMapper extends Mapper<Menu> {

    List<Menu> listByPage(Map<String, Object> map);

    List<MenuDto> getPermsByRoleId(String roleId);

    List<MenuDto> getMenuCodeByRoleId(String roleId);
}
