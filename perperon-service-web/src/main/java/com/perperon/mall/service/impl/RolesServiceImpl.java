package com.perperon.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.dto.MenuDto;
import com.perperon.mall.dto.RolesDto;
import com.perperon.mall.mapper.AccountRoleMapper;
import com.perperon.mall.mapper.MenuMapper;
import com.perperon.mall.mapper.RoleMenuMapper;
import com.perperon.mall.mapper.RolesMapper;
import com.perperon.mall.pojo.AccountRole;
import com.perperon.mall.pojo.RoleMenu;
import com.perperon.mall.pojo.Roles;
import com.perperon.mall.service.RolesService;
import org.springframework.beans.BeanUtils;
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

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private AccountRoleMapper accountRoleMapper;

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
            //将角色所属权限转化为树形结构并分配给角色
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

    public List<String> getUserAuthority(String id){
        List<String> authList = new ArrayList<>();
        //根据用户id获取所有的角色信息
        List<Roles> roleList = rolesMapper.getRolesById(id);
        if (roleList.size() > 0){
            String collect = roleList.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
            authList.add(collect);
        }
        //遍历角色信息，获取权限标识，而且不重复
        Set<String> menuCodeSet  = new HashSet<>();
        for(Roles role : roleList) {
            List<MenuDto> menuList = menuMapper.getPermsByRoleId(role.getId());
            for(MenuDto menu : menuList) {
                if (StrUtil.isNotBlank(menu.getPerms())){
                    menuCodeSet.add(menu.getPerms());
                }
            }
        }
        if (menuCodeSet.size() > 0){
            List<String> collect = Arrays.asList(menuCodeSet.toArray(new String[0]));
            authList.addAll(collect);
        }
        System.out.println("auth"+authList);
        return authList;
    }

    @Override
    public List<Roles> getRoleList(String accountId) {
        List<Roles> roles = rolesMapper.getRolesById(accountId);
        return roles;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public CommonResult<Roles> grantRole(String accountId, List<String> roleIds) {
        List<AccountRole> accountRoles = new ArrayList<>();
        roleIds.forEach(id -> {
            AccountRole accountRole = new AccountRole();
            accountRole.setAccountId(accountId);
            accountRole.setRoleId(id);
            accountRoles.add(accountRole);
        });
        //删除已有用户角色关系重新分配
        Example example = new Example(AccountRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("accountId", accountId);
        accountRoleMapper.deleteByExample(example);
        accountRoleMapper.saveList(accountRoles);
        return CommonResult.success(null, "分配成功");
    }
}
