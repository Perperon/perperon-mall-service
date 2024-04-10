package com.perperon.mall.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author dupengcheng
 * @date 2024-04-10
 * @apiNote
 */
@Configuration
public class BaseConfig {
    @Bean
    @LoadBalanced //@LoadBalance赋予RestTemplate负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
