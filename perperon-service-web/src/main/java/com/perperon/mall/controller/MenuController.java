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


    @GetMapping(value = "/listPage")
    @ApiOperation(value = "查询菜单", notes = "查询菜单")
    public Object listPage(@RequestParam Map<String,Object> params) {
        return menuService.listByPage(params);
    }

    @ApiOperation("树形结构返回所有菜单列表")
    @GetMapping(value = "/treeList")
    @ResponseBody
    public CommonResult<List<MenuDto>> treeList() {
        List<MenuDto> list = menuService.treeList();
        return CommonResult.success(list);
    }
}
