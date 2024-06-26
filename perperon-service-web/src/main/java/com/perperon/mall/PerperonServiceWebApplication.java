package com.perperon.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {"com.perperon.mall"})
@EnableFeignClients
@EnableDiscoveryClient
public class PerperonServiceWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerperonServiceWebApplication.class, args);
    }

}
