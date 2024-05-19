package com.perperon.mall.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 *Swagger资源配置
 * @author perperon
 * @date 2024-04-15
 * @apiNote
 */
@Slf4j
@Component
@Primary
@AllArgsConstructor
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

        @Override
        public List<SwaggerResource> get() {
            List<SwaggerResource> resources = new ArrayList<>();
            List<String> routes = new ArrayList<>();

            //获取所有路由的ID
            routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

            // 过滤出配置文件中定义的路由
            // -> 过滤出Path Route Predicate
            // -> 根据路径拼接成api-docs路径
            // -> 生成SwaggerResource
            //过滤出配置文件中定义的路由->过滤出Path Route Predicate->根据路径拼接成api-docs路径->生成SwaggerResource
            gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId())).forEach(route -> {
                route.getPredicates().stream()
                        // 过滤出Path Route Predicate
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> {
                            // 根据路径拼接成api-docs路径
                            // 生成SwaggerResource
                            resources.add(swaggerResource(route.getId(),
                                    predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                            .replace("**", "v2/api-docs")));
                        });
            });

            return resources;
        }


        private SwaggerResource swaggerResource(String name, String location) {
            // 输出日志，显示name和location的值
            log.info("name:{},location:{}", name, location);

            // 创建SwaggerResource对象
            SwaggerResource swaggerResource = new SwaggerResource();

            // 设置SwaggerResource对象的name属性
            swaggerResource.setName(name);

            // 设置SwaggerResource对象的location属性
            swaggerResource.setLocation(location);

            // 设置SwaggerResource对象的swaggerVersion属性为"2.0"
            swaggerResource.setSwaggerVersion("2.0");

            // 返回SwaggerResource对象
            return swaggerResource;
        }

}
