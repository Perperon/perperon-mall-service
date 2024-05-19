package com.perperon.mall.controller;

import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.pojo.Account;
import com.perperon.mall.service.AccountService;
import com.perperon.mall.service.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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


    @GetMapping(value = "/listPage")
    @ApiOperation(value = "查询用户", notes = "查询用户")
    @PreAuthorize("hasAuthority('list')")
    public Object listPage(@RequestParam Map<String,Object> params) {
        return accountService.listByPage(params);
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public Object logout() {
        return accountService.logout();
    }
}
