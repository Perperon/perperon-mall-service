package com.perperon.mall.config;

import com.perperon.mall.fliter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author perperon
 * @date 2024/5/13
 * @apiNote 配置Spring Security的过滤器链
 *@EnableWebSecurity
 * 自动配置: 它告诉 Spring Boot 自动配置一个 WebSecurityConfiguration，该配置会设置默认的 web 安全特性。
 *
 * 创建 AuthenticationManager: 在安全配置中，会创建一个 AuthenticationManager bean，该 bean 负责处理认证请求。
 *
 * 创建 HttpSecurity: 还会创建一个 HttpSecurity bean，它提供了配置 web 安全的方法，比如配置登录页面、登出页面、权限控制等。
 *
 * 创建默认的用户详情服务: 如果没有定义自己的用户详情服务（UserDetailsService），Spring Security 会提供一个默认的。
 *
 * 创建默认的认证管理器: 如果没有定义自己的 AuthenticationManager，Spring Security 会创建一个默认的。
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true) //开启方法级权限控制
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();

            for (String url : ignoreUrlsConfig.getUrls()) {
                 registry.antMatchers(url).permitAll();
            }

            registry
                //允许跨域请求的OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                //1.配置所有对静态资源的访问都放行
                //.antMatchers( "/assets/**").permitAll()
                //未登录时登录请求可以公开或匿名访问，登录状态中不能访问
                .antMatchers("/account/login").anonymous()
                //其他请求都需登录认证才能访问
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable()
                //不通过session来保持状态，使用jwt
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                // 自定义权限拒绝处理类
                .accessDeniedHandler(accessDeniedHandler)
                // 自定义认证失败处理类
                .authenticationEntryPoint(authenticationEntryPoint)
                // 自定义JWT认证过滤器
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
