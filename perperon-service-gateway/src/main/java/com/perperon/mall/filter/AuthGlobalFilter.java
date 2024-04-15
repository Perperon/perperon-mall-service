/*
package com.perperon.mall.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

*/
/**
 * @author dupengcheng
 * @date 2024-04-15
 * @apiNote 全局过滤器
 *//*

@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            log.info("custom global filter");

            // 获取请求参数中的uname
            String uname = exchange.getRequest().getQueryParams().getFirst("uname");

            // 如果uname为空
            if(uname== null){
                // 记录日志：非法访问
                log.info("非法访问");

                // 设置响应状态码为不可接受
                exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);

                // 完成响应，并返回
                return exchange.getResponse().setComplete();
            }

            // 继续执行过滤器链
            return chain.filter(exchange);
        }

    @Override
    public int getOrder() {
        return -1;
    }
}
*/
