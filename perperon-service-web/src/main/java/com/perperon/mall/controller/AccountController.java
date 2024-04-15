package com.perperon.mall.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dupengcheng
 * @date 2024-04-09
 * @apiNote
 */
@RestController
@RequestMapping("/account")
@Api(tags = "账户管理模块")
public class AccountController {

    @GetMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public String login() {
        return "login";
    }
}
