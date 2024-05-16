package com.perperon.mall.entity;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.perperon.mall.pojo.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author perperon
 * @date 2024/5/10
 * @apiNote
 */
@Data
@NoArgsConstructor
//@JsonIgnoreProperties({"enabled","credentialsNonExpired","accountNonLocked","accountNonExpired","authorities"})
//@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUser implements UserDetails {

    private Account account;
    //存储权限标识字符串集合
    private List<String> permissions;
    //GrantedAuthority对象中封装权限标识字符串集合
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    public AccountUser(Account account, List<String> permissions) {
        this.account = account;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //将代表权限的标识字符串，封装到GrantedAuthority对象中
        authorities = CollectionUtil.isEmpty(authorities) ? permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()) : authorities;
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
