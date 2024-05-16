package com.perperon.mall.security;

import cn.hutool.core.util.ObjectUtil;
import com.perperon.mall.entity.AccountUser;
import com.perperon.mall.mapper.AccountMapper;
import com.perperon.mall.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author perperon
 * @date 2024/5/10
 * @apiNote
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountMapper.selectByUsername(username);
        if (ObjectUtil.isNull(account)) {
            throw new RuntimeException("用户名不存在");
        }
        //查询对应权限信息
        ArrayList<String> auth = new ArrayList<>(Arrays.asList("list", "add"));

        return new AccountUser(account,auth);
    }
}
