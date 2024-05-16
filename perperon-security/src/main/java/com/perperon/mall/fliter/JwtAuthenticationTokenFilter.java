package com.perperon.mall.fliter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.perperon.mall.cache.RedisCache;
import com.perperon.mall.entity.AccountUser;
import com.perperon.mall.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录认证过滤器
 * @author perperon
 * @date 2024/5/12
 * @apiNote
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisCache redisCache;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    /*@Value("${jwt.tokenHead}")
    private String tokenHead;*/
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader(this.tokenHeader);
        if (StrUtil.isNotEmpty(authToken)) {
            //String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            LOGGER.info("checking username:{}", username);
            if (StrUtil.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                AccountUser accountUser = redisCache.getAdmin(username);
                if (ObjectUtil.isNull(accountUser)) {
                    LOGGER.info("token:{} is invalid", authToken);
                    throw new RuntimeException("用户未登录！");
                }
                if (jwtTokenUtil.validateToken(authToken, accountUser)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(accountUser, null, accountUser.getAuthorities());
                    //authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
