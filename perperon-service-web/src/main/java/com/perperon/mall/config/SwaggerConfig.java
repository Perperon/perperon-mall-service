package com.perperon.mall.config;

import com.perperon.mall.common.config.BaseSwaggerConfig;
import com.perperon.mall.common.domain.SwaggerProperties;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author perperon
 * @date 2024-04-10
 * @apiNote Swagger API文档相关配置
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.perperon.mall.controller")
                .title("perperon-mall后台系统")
                .description("perperon-mall后台相关接口文档")
                .contactName("perperon")
                .version("1.0")
                .enableSecurity(false)
                .build();
    }

    @Bean
    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        // 调用 generateBeanPostProcessor() 方法生成 BeanPostProcessor 实例
        return generateBeanPostProcessor();
    }

}
