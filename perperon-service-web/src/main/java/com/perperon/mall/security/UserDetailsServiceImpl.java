package com.perperon.mall.security;

import cn.hutool.core.util.ObjectUtil;
import com.perperon.mall.common.exception.Asserts;
import com.perperon.mall.entity.AccountUser;
import com.perperon.mall.mapper.AccountMapper;
import com.perperon.mall.pojo.Account;
import com.perperon.mall.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author perperon
 * @date 2024/5/10
 * @apiNote
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RolesService rolesService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountMapper.selectByUsername(username);
        if (ObjectUtil.isNull(account)) {
            Asserts.fail("用户名不存在");
        }
        if (!account.getStatus()) {
            Asserts.fail("用户已被禁用");
        }
        //查询对应权限信息
        List<String> userAuthority = rolesService.getUserAuthority(account.getId());

        return new AccountUser(account,userAuthority);
    }
}
