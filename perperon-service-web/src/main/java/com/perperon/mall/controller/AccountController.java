package com.perperon.mall.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dupengcheng
 * @date 2024-04-09
 * @apiNote
 */
@RestController
@RefreshScope
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
