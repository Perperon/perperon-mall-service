package com.perperon.mall.config;

import com.perperon.mall.fliter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author perperon
 * @date 2024/5/13
 * @apiNote
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //1.配置所有静态资源和登录页可以公开访问
                .antMatchers( "/assets/**").permitAll()
                .antMatchers("/account/login").anonymous()
                .anyRequest().authenticated()
                .and()
                //2.配置登录和登出路径
                .formLogin().loginPage( "/login")
                .and()
                .logout().logoutUrl( "/logout")
                .and()
                //3.开启http basic支持，admin-client注册时需要使用
                .httpBasic()
                .and()
                .csrf()
                .disable()
                //不通过session来保持状态，使用jwt
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拦截器JWT过滤器
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
