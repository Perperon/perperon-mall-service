package com.perperon.mall.aspect;

import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * @author dupengcheng
 * @date 2024-07-03
 * @apiNote
 */
@Component("ps")
public class AuthorityPerms{
    private RoleHierarchy roleHierarchy;

    private Set<String> roles;


    // 假设你有一个方法来获取当前用户的角色和权限
    // 这里只是一个示例，你需要根据实际情况来实现它
    private final Set<String> getCurrentUserPermissions() {
        // ... 获取当前用户的权限，并返回一个字符串集合
        Set<String> roleSet = this.getAuthoritySet();
        return roleSet;
    }

    private Set<String> getAuthoritySet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (this.roles == null) {
            Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
            if (roleHierarchy != null) {
                userAuthorities = roleHierarchy.getReachableGrantedAuthorities(userAuthorities);
            }

            roles = AuthorityUtils.authorityListToSet(userAuthorities);
        }
        return roles;
    }

    public final boolean hasPerm(String pattern) {
        // TODO: 2024/7/3 权限判断
        // 遍历当前用户的权限，检查是否有任何一个匹配给定的通配符模式
        for (String permission : getCurrentUserPermissions()) {
            if (matches(permission, pattern)) {
                return true;
            }
        }
        return false;
    }

    // 一个简单的通配符匹配方法
    // 注意：这个实现非常简单，只支持*作为通配符
    private boolean matches(String permission, String pattern) {
        // 使用String.matches方法来进行模式匹配
        // 这里假设*匹配任何子字符串
        return permission.matches(pattern.replace("*", ".*"));
    }

}
