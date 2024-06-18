package com.perperon.mall.controller;

import com.perperon.mall.pojo.Roles;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.RolesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
