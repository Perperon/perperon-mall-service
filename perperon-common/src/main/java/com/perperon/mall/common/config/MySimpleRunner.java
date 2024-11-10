package com.perperon.mall.common.config;

import org.springframework.boot.CommandLineRunner;

/**
 * @author dupengcheng
 * @date 2024/11/10
 * @apiNote
 */
public class MySimpleRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MySimpleRunner 执行了");
    }
}
