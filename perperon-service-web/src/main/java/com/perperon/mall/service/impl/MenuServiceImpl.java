package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.perperon.mall.dto.MenuDto;
import com.perperon.mall.mapper.MenuMapper;
import com.perperon.mall.pojo.Menu;
import com.perperon.mall.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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
        List<Menu> menuList = menuMapper.selectAll();
        List<MenuDto> result = menuList.stream()
                .filter(menu -> StrUtil.isBlank(menu.getParentId()))
                .map(menu -> covertMenuNode(menu, menuList))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 将Menu转化为MenuDto并设置children属性
     */
    private MenuDto covertMenuNode(Menu menu, List<Menu> menuList) {
        MenuDto node = new MenuDto();
        BeanUtils.copyProperties(menu, node);
        List<MenuDto> children = menuList.stream()
                .filter(subMenu -> menu.getId().equals(subMenu.getParentId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
