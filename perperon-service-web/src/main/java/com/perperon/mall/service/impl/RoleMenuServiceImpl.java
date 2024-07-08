package com.perperon.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.mapper.RoleMenuMapper;
import com.perperon.mall.pojo.RoleMenu;
import com.perperon.mall.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-06-18
 * @apiNote
 */
@Transactional(readOnly = true)
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Mapper<RoleMenu> getMapper() {
        return roleMenuMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return new PageInfo(roleMenuMapper.listByPage(params));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public CommonResult<RoleMenu> deleteByRole(Map<String, Object> params) {
        Example example = new Example(RoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", params.get("roleId"));
        criteria.andEqualTo("menuId", params.get("menuId"));
        roleMenuMapper.deleteByExample(example);
        return CommonResult.success(null, "删除权限成功");
    }
}
