package com.perperon.mall.cache;

import com.perperon.mall.common.service.RedisService;
import com.perperon.mall.entity.AccountUser;
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

    @Value("${jwt.tokenHead}")
    private String TOKEN_HEAD;
    @Autowired
    private RedisService redisService;
    @Resource
    JwtTokenUtil jwtTokenUtil;

    /**
     * 设置缓存后台用户信息与token
     */
    public String setAdmin(AccountUser admin){
        String jwt = TOKEN_HEAD + jwtTokenUtil.generateToken(admin);
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
        return jwt;
    }

    /**
     * 获取缓存后台用户信息
     * @param username
     * @return
     */
    public AccountUser getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (AccountUser) redisService.get(key);
    }

    /**
     * 删除缓存后台用户信息
     */
    public void delAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        redisService.del(key);
    }
}
