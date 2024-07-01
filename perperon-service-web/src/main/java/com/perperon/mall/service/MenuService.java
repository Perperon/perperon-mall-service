package com.perperon.mall.service;

import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.dto.MenuDto;
import com.perperon.mall.pojo.Menu;

import java.util.List;
import java.util.Map;

/**
 * @author perperon
 * @date 2024-04-15
 * @apiNote
 */
public interface MenuService extends BaseService<Menu> {


    /**
     * 树形结构返回角色所拥有的菜单列表
     */
    List<MenuDto> treeList();


    List<MenuDto> treeMenuList(Map<String,Object> params);

    CommonResult<List<String>> menuRoleList(String roleId);

    CommonResult<List<String>> grantMenu(String roleId, List<String> menuIds);
}
