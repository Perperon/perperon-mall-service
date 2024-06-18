package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.perperon.mall.dto.MenuDto;
import com.perperon.mall.dto.RolesDto;
import com.perperon.mall.mapper.RoleMenuMapper;
import com.perperon.mall.mapper.RolesMapper;
import com.perperon.mall.pojo.RoleMenu;
import com.perperon.mall.pojo.Roles;
import com.perperon.mall.service.RolesService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dupengcheng
 * @date 2024-06-18
 * @apiNote
 */
@Transactional(readOnly = true)
@Service
public class RolesServiceImpl implements RolesService {

    @Resource
    private RolesMapper rolesMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Mapper<Roles> getMapper() {
        return rolesMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        //查询出所有角色
        List<RolesDto> roles = rolesMapper.listByPage(params);
        for (RolesDto role : roles) {
            //查询出角色所有权限
            List<RoleMenu> roleMenus = roleMenuMapper.listByRoleId(role.getId());
            List<MenuDto> result = roleMenus.stream()
                    .filter(menu -> StrUtil.isBlank(menu.getParentId()))
                    .map(menu -> covertMenuNode(menu, roleMenus))
                    .collect(Collectors.toList());
            role.setChildren(result);
        }
        return new PageInfo(roles);
    }

    /**
     * 将Menu转化为MenuDto并设置children属性
     */
    private MenuDto covertMenuNode(RoleMenu roleMenu, List<RoleMenu> menuList) {
        MenuDto node = new MenuDto();
        BeanUtils.copyProperties(roleMenu, node);
        List<MenuDto> children = menuList.stream()
                .filter(subMenu -> roleMenu.getMenuId().equals(subMenu.getParentId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
