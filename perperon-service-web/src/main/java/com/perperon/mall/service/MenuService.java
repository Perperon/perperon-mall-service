package com.perperon.mall.service;

import com.perperon.mall.dto.MenuDto;
import com.perperon.mall.pojo.Menu;

import java.util.List;

/**
 * @author perperon
 * @date 2024-04-15
 * @apiNote
 */
public interface MenuService extends BaseService<Menu> {


    /**
     * 树形结构返回所有菜单列表
     */
    List<MenuDto> treeList();
}
