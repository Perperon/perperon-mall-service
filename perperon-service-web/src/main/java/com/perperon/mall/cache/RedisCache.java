package com.perperon.mall.cache;

import com.perperon.mall.common.service.RedisService;
import com.perperon.mall.entity.AccountUser;
import com.perperon.mall.pojo.Account;
import com.perperon.mall.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author perperon
 * @date 2024/5/13
 * @apiNote  后台用户缓存token
 */

@Component
public class RedisCache {
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.token}")
    private String REDIS_KEY_TOKEN;
    @Autowired
    private RedisService redisService;
    @Resource
    JwtTokenUtil jwtTokenUtil;

    /**
     * 设置缓存后台用户信息与token
     */
    public void setAdmin(AccountUser admin){
        String jwt = jwtTokenUtil.generateToken(admin);
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        String tokenKey = REDIS_DATABASE + ":" + REDIS_KEY_TOKEN + ":" + admin.getUsername();
        redisService.set(key, admin.getAccount(), REDIS_EXPIRE);
        redisService.set(tokenKey, jwt, REDIS_EXPIRE);
    }

    /**
     * 获取缓存后台用户信息
     * @param username
     * @return
     */
    public Account getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (Account) redisService.get(key);
    }
}
