package com.perperon.mall.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.perperon.mall.cache.RedisCache;
import com.perperon.mall.common.exception.Asserts;
import com.perperon.mall.common.response.ResultCode;
import com.perperon.mall.entity.AccountUser;
import com.perperon.mall.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author dupengcheng
 * @date 2024-06-21
 * @apiNote
 */
@Component
public class CurrentAccountUtil {

    @Autowired
    private HttpServletRequest request;



    public static Account getCurrentAdmin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorization = request.getHeader("Authorization");
        if(StrUtil.isEmpty(authorization)){
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        String authToken = authorization.substring("Bearer ".length());
        JwtTokenUtil jwtTokenUtil = SpringUtil.getBean(JwtTokenUtil.class);
        String username = jwtTokenUtil.getUserNameFromToken(authToken);

        RedisCache redisCache = SpringUtil.getBean(RedisCache.class);
        AccountUser accountUser = redisCache.getAdmin(username);

        if(Objects.nonNull(accountUser)){
            return accountUser.getAccount();
        }else{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object object = authentication.getPrincipal();
                AccountUser accountuser = null;
                if (object instanceof Account) {
                    accountuser = (AccountUser) object;
                    return accountuser.getAccount();
                }
            }
        }
        return null;
    }
}
