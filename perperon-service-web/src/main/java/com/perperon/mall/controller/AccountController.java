package com.perperon.mall.controller;

import com.perperon.mall.pojo.Account;
import com.perperon.mall.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-04-09
 * @apiNote
 */
@RestController
@RequestMapping("/account")
@Api(tags = "账户管理模块")
public class AccountController {

    @Autowired
    private AccountService accountServiceImpl;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public String login(@RequestBody Account account) {
        userDetailsService.loadUserByUsername(account.getUsername());
        return "login";
    }


    @GetMapping(value = "/listPage")
    @ApiOperation(value = "查询用户", notes = "查询用户")
    public Object listPage(@RequestParam Map<String,Object> params) {
        return accountServiceImpl.listByPage(params);
    }

    @PostMapping(value = "/create")
    @ApiOperation(value = "新增用户", notes = "新增用户")
    public Object create(@RequestBody Account account) {
        return accountServiceImpl.create(account);
    }
}
