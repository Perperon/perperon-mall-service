package com.perperon.mall.aspect;

import com.perperon.mall.cache.RedisCache;
import com.perperon.mall.entity.AccountUser;
import com.perperon.mall.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author dupengcheng
 * @date 2024-07-03
 * @apiNote 自定义权限实现
 */
@Component("ps")
public class AuthorityPerms{
    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*";
    /**
     * 管理员角色权限标识
     */
    private static final String SUPER_ADMIN = "admin";
    private static final String ROLE_DELIMETER = ",";
    private static final String PERMISSION_DELIMETER = ",";

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    RedisCache redisCache;
    @Autowired
    private HttpServletRequest request;

    public AccountUser getLoginUser(){
        String authHeader = request.getHeader("Authorization");
        String authToken = authHeader.substring("Bearer ".length());
        String username = jwtTokenUtil.getUserNameFromToken(authToken);
        return redisCache.getAdmin(username);
    }
    /**
     * 验证用户是否具备某权限
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPerm(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        AccountUser accountUser = getLoginUser();
        if (Objects.isNull(accountUser) || CollectionUtils.isEmpty(accountUser.getPermissions())) {
            return false;
        }
        return hasPermissions(accountUser.getPermissions(), permission);
    }
    /**
     * 验证用户是否不具备某权限，与 hasPermi逻辑相反
     * @param permission 权限字符串
     * @return 用户是否不具备某权限
     */
    public boolean lacksPerm(String permission) {
        return hasPerm(permission) != true;
    }
    /**
     * 验证用户是否具有以下任意一个权限
     * @param permissions 以 PERMISSION_NAMES_DELIMETER 为分隔符的权限列表
     * @return 用户是否具有以下任意一个权限
     */
    public boolean hasAnyPerm(String permissions) {
        if (StringUtils.isEmpty(permissions)) {
            return false;
        }
        AccountUser accountUser = getLoginUser();
        if (Objects.isNull(accountUser) || CollectionUtils.isEmpty(accountUser.getPermissions())) {
            return false;
        }
        List<String> authorities = accountUser.getPermissions();
        for (String permission : permissions.split(PERMISSION_DELIMETER)) {
            if (permission != null && hasPermissions(authorities, permission)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 判断用户是否拥有某个角色
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
//    public boolean hasRole(String role) {
//        if (StringUtils.isEmpty(role)) {
//            return false;
//        }
//        AccountUser accountUser = getLoginUser();
//        if (Objects.isNull(accountUser) || CollectionUtils.isEmpty(accountUser.getAccount().getRoles())) {
//            return false;
//        }
//        for (Roles roles : accountUser.getAccount().getRoles()) {
//            String roleKey = roles.getRoleKey();
//            if (SUPER_ADMIN.equals(roleKey) || roleKey.equals(StringUtils.trim(role))) {
//                return true;
//            }
//        }
//        return false;
//    }
    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     * @param role 角色名称
     * @return 用户是否不具备某角色
     */
//    public boolean lacksRole(String role) {
//        return hasRole(role) != true;
//    }
    /**
     * 验证用户是否具有以下任意一个角色
     * @param roles 以 ROLE_NAMES_DELIMETER 为分隔符的角色列表
     * @return 用户是否具有以下任意一个角色
     */
//    public boolean hasAnyRoles(String roles) {
//        if (StringUtils.isEmpty(roles)) {
//            return false;
//        }
//        LoginUser loginUser = SecurityUtils.getLoginUser();
//        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles())) {
//            return false;
//        }
//        for (String role : roles.split(ROLE_DELIMETER)) {
//            if (hasRole(role)) {
//                return true;
//            }
//        }
//        return false;
//    }
    /**
     * 判断是否包含权限
     * @param permissions 权限列表
     * @param permission  权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermissions(List<String> permissions, String permission) {
        String[] split = permission.split(":");
        if (split[0].equals("*")){
            return true;
        }
        return permissions.contains(ALL_PERMISSION) || permissions.contains(StringUtils.trim(permission));
    }

}
