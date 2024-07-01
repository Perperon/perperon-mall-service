package com.perperon.mall.controller;

import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.dto.MenuDto;
import com.perperon.mall.pojo.Menu;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author perperon
 * @date 2024-04-09
 * @apiNote 菜单管理模块
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理模块")
public class MenuController extends BaseController<Menu>{

    @Autowired
    private MenuService menuService;


    @Override
    public BaseService<Menu> getService() {
        return menuService;
    }


    @GetMapping(value = "/treeMenuList")
    @ApiOperation("树形结构返回所有菜单列表")
    public CommonResult<List<MenuDto>> treeMenuList(Map<String,Object> params) {
        List<MenuDto> list = menuService.treeMenuList(params);
        return CommonResult.success(list);
    }

    @GetMapping(value = "/menuRoleList/{roleId}")
    @ApiOperation("查询角色所拥有的菜单权限")
    public Object menuRoleList(@PathVariable("roleId") String roleId) {
        return menuService.menuRoleList(roleId);
    }

    @PostMapping("/grantMenu")
    @ApiOperation(value = "角色分配菜单权限", notes = "角色分配菜单权限")
    public Object grantMenu(@RequestBody Map<String, Object> params) {
        List<String> menus = (ArrayList) params.get("menus");
        return menuService.grantMenu(params.get("roleId").toString(), menus);
    }
}
