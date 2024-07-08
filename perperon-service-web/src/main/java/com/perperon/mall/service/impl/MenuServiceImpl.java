package com.perperon.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.dto.MenuDto;
import com.perperon.mall.dto.RolesDto;
import com.perperon.mall.entity.AccountUser;
import com.perperon.mall.mapper.MenuMapper;
import com.perperon.mall.mapper.RoleMenuMapper;
import com.perperon.mall.mapper.RolesMapper;
import com.perperon.mall.pojo.Menu;
import com.perperon.mall.pojo.RoleMenu;
import com.perperon.mall.pojo.Roles;
import com.perperon.mall.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author perperon
 * @date 2024/4/15
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RolesMapper rolesMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Mapper<Menu> getMapper() {
        return menuMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return new PageInfo(menuMapper.listByPage(params));
    }

    @Override
    public List<MenuDto> treeList() {
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountUser accountUser = (AccountUser) authentication.getPrincipal();
        List<Roles> roleList = rolesMapper.getRolesById(accountUser.getAccount().getId());

        Set<MenuDto> menuSet  = new HashSet<>();
        for(Roles role : roleList) {
            List<MenuDto> menuList = menuMapper.getMenuCodeByRoleId(role.getId());
            for(MenuDto menu : menuList) {
                menuSet.add(menu);
            }
        }
        List<MenuDto> menuList = new ArrayList<>(menuSet);
        //排序
        menuList.sort(Comparator.nullsLast(Comparator.comparingInt(Menu::getSort)));

        List<MenuDto> result = menuList.stream()
                .filter(menu -> StrUtil.isBlank(menu.getParentId()))
                .map(menu -> covertMenuNode(menu, menuList))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<MenuDto> treeMenuList(Map<String,Object> params) {
        // 查询所有菜单
        List<MenuDto> menuList = menuMapper.listByPage(params);
        //排序
        menuList.sort(Comparator.nullsLast(Comparator.comparingInt(Menu::getSort)));
        List<MenuDto> result = menuList.stream()
                .filter(menu -> StrUtil.isBlank(menu.getParentId()))
                .map(menu -> covertMenuNode(menu, menuList))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 将Menu转化为MenuDto并设置children属性
     */
    private MenuDto covertMenuNode(MenuDto menu, List<MenuDto> menuList) {
        MenuDto node = new MenuDto();
        BeanUtils.copyProperties(menu, node);
        List<MenuDto> children = menuList.stream()
                .filter(subMenu -> menu.getId().equals(subMenu.getParentId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    @Override
    public CommonResult<RolesDto> roleMenuList(String roleId) {
        RolesDto roles = new RolesDto();
        Set<MenuDto> menuSet  = new HashSet<>();
        List<MenuDto> menuList = menuMapper.getMenuByRoleId(roleId);
        for(MenuDto menu : menuList) {
            menuSet.add(menu);
        }
        List<MenuDto> menus = new ArrayList<>(menuSet);
        List<MenuDto> result = menus.stream()
                .filter(menu -> StrUtil.isBlank(menu.getParentId()))
                .map(menu -> covertMenuNode(menu, menus))
                .collect(Collectors.toList());
        roles.setChildren(result);
        return CommonResult.success(roles);
    }

    @Override
    public CommonResult<List<String>> menuRoleList(String roleId) {
        List<RoleMenu> roleMenus = roleMenuMapper.listByRoleId(roleId);
        List<String> menuIds = roleMenus.stream().map(e -> e.getMenuId()).collect(Collectors.toList());
        return CommonResult.success(menuIds);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public CommonResult<List<String>> grantMenu(String roleId, List<String> menuIds) {
        List<RoleMenu> roleMenus = new ArrayList<>();
        menuIds.forEach(id -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(id);
            roleMenus.add(roleMenu);
        });
        //删除已有角色菜单关系 重新分配
        Example example = new Example(RoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        roleMenuMapper.deleteByExample(example);
        roleMenuMapper.saveList(roleMenus);
        return CommonResult.success(null, "分配菜单成功");
    }
}
