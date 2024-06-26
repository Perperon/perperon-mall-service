package com.perperon.mall.controller;

import cn.hutool.core.collection.CollUtil;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.pojo.Account;
import com.perperon.mall.pojo.Roles;
import com.perperon.mall.service.AccountService;
import com.perperon.mall.service.BaseService;
import com.perperon.mall.service.MenuService;
import com.perperon.mall.service.RolesService;
import com.perperon.mall.utils.CurrentAccountUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author perperon
 * @date 2024-04-09
 * @apiNote
 */
@RestController
@RequestMapping("/account")
@Api(tags = "账户管理模块")
public class AccountController extends BaseController<Account>{

    @Autowired
    private AccountService accountService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RolesService rolesService;


    @Override
    public BaseService<Account> getService() {
        return accountService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public Object login(@RequestBody Account account) {
        CommonResult<Account> login = accountService.login(account);
        return login;
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public Object logout() {
        return accountService.logout();
    }

    @PutMapping("/updatePwd")
    @ApiOperation("修改密码")
    public Object updatePwd(@RequestBody Account obj){
        return accountService.updatePwd(obj);
    }

    @PutMapping("/resetPwd/{id}")
    @ApiOperation("重置密码")
    public Object resetPwd(@PathVariable("id") String id){
        return accountService.resetPwd(id);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getInfo() {
        Account account = CurrentAccountUtil.getCurrentAdmin();
        Map<String, Object> data = new HashMap<>();
        data.put("nickName", account.getNickName());
        data.put("menus", menuService.treeList());
        data.put("icon", account.getIcon());
        List<Roles> roleList = rolesService.getRoleList(account.getId());
        account.setRoles(roleList.stream().map(Roles::getName).collect(Collectors.joining(",")));
        data.put("userInfo", account);
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(Roles::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }
}
