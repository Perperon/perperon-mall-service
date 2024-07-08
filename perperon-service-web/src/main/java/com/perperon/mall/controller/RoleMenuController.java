package com.perperon.mall.controller;

import com.perperon.mall.pojo.RoleMenu;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author perperon
 * @date 2024-04-09
 * @apiNote 角色管理模块
 */
@RestController
@RequestMapping("/rolemenu")
@Api(tags = "角色菜单管理模块")
public class RoleMenuController extends BaseController<RoleMenu>{

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public BaseService<RoleMenu> getService() {
        return roleMenuService;
    }

    @DeleteMapping("/deleteByRole")
    @ApiOperation(value = "删除角色权限", notes = "删除角色权限")
    public Object deleteByRole(@RequestBody Map<String, Object> params) {
        return roleMenuService.deleteByRole(params);
    }
}
