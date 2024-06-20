package com.perperon.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.perperon.mall.dto.MenuDto;
import com.perperon.mall.entity.AccountUser;
import com.perperon.mall.mapper.MenuMapper;
import com.perperon.mall.mapper.RolesMapper;
import com.perperon.mall.pojo.Menu;
import com.perperon.mall.pojo.Roles;
import com.perperon.mall.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

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
        menuList.sort(Comparator.comparingInt(Menu::getSort));

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
}
