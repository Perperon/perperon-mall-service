package com.perperon.mall.controller;

import com.perperon.mall.pojo.Menu;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping(value = "/listPage")
    @ApiOperation(value = "查询菜单", notes = "查询菜单")
    @PreAuthorize("hasAuthority('list')")
    public Object listPage(@RequestParam Map<String,Object> params) {
        return menuService.listByPage(params);
    }

}
