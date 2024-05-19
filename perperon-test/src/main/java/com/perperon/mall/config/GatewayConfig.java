package com.perperon.mall.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author perperon
 * @date 2024-04-15
 * @apiNote Gateway配置类
 */

@Configuration
public class GatewayConfig {
        @Bean
        public RouteLocator custRouteLocator(RouteLocatorBuilder routeLocatorBuilder ){
            // 创建路由构建器
            RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
            // 定义路由规则
            routes.route("baidu-test",
                    // 请求路径匹配 "/guonei"
                    r -> r.path("/guonei")
                            // 目标URI为 "https://www.baidu.com"
                            .uri("http://news.baidu.com"));

            // 构建并返回整个路由定位器
            return routes.build();
        }

}
