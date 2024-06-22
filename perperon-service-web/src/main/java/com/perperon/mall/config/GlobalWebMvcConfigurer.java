package com.perperon.mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author perperon
 * @date 2024/6/21
 * @apiNote
 */
@Configuration
public class GlobalWebMvcConfigurer implements WebMvcConfigurer {
    @Value("${serverConfig.attach}")
    private String attach;

    @Value("${serverConfig.uploadPath}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(attach+"/**").addResourceLocations("file:"+uploadPath);
    }
}
