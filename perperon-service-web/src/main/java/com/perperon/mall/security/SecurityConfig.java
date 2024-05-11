package com.perperon.mall.security;

import com.perperon.mall.utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author perperon
 * @date 2024/5/11
 * @apiNote
 */
@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   /* @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }*/

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //1.配置所有静态资源和登录页可以公开访问
                .antMatchers( "/assets/**").permitAll()
                .antMatchers("/account/login").anonymous()
                .anyRequest().authenticated()
                .and()
                //2.配置登录和登出路径
                .formLogin().loginPage( "/login").and()
                .logout().logoutUrl( "/logout").and()
                //3.开启http basic支持，admin-client注册时需要使用
                .httpBasic().and()
                .csrf()
                .disable()
                //不通过session来保持状态，使用jwt
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
