package com.perperon.mall.controller;

import com.perperon.mall.pojo.Roles;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.RolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author perperon
 * @date 2024-04-09
 * @apiNote 角色管理模块
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理模块")
public class RolesController extends BaseController<Roles>{

    @Autowired
    private RolesService rolesService;

    @Override
    public BaseService<Roles> getService() {
        return rolesService;
    }

    @PostMapping("/grantRole")
    @ApiOperation(value = "用户加角色", notes = "用户加角色")
    public Object grantRole(@RequestBody Map<String, Object> params) {
        List<String> rolesIds = (ArrayList) params.get("rolesIds");
        return rolesService.grantRole(params.get("accountId").toString(), rolesIds);
    }
}
