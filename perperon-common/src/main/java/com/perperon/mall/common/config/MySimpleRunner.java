package com.perperon.mall.common.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author dupengcheng
 * @date 2024/11/10
 * @apiNote
 */
@Component
public class MySimpleRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MySimpleRunner 执行了");
    }
}
