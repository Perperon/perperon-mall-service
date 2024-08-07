package com.perperon.mall.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.github.pagehelper.PageInfo;
import com.perperon.mall.cache.RedisCache;
import com.perperon.mall.common.exception.Asserts;
import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.entity.AccountUser;
import com.perperon.mall.mapper.AccountMapper;
import com.perperon.mall.pojo.Account;
import com.perperon.mall.service.AccountService;
import com.perperon.mall.utils.CurrentAccountUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author perperon
 * @date 2024/4/15
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl  implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    RedisCache redisCache;

    @Override
    public Mapper<Account> getMapper() {
        return accountMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        return new PageInfo(accountMapper.listByPage(params));
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public CommonResult login(Account account) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(ObjectUtil.isNull(authenticate)){
            Asserts.fail("登录失败");
        }

        //认证成功，获取用户信息，生成jwt
        AccountUser accountUser = (AccountUser)authenticate.getPrincipal();
        //更新登录时间
        account = accountUser.getAccount();
        account.setLoginTime(new Date());
        accountMapper.updateByPrimaryKeySelective(account);
        String jwt = redisCache.setAdmin(accountUser);
        Map<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        return CommonResult.success(map,"登录成功！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResult<Account> resetPwd(String id) {
        Account user = accountMapper.selectByPrimaryKey(id);
        BCryptPasswordEncoder encoder = SpringUtil.getBean(BCryptPasswordEncoder.class);
        user.setPassword(encoder.encode("123456"));
        accountMapper.updateByPrimaryKeySelective(user);
        return CommonResult.success(user,"重置密码成功！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResult<Account> updatePwd(Account account) {
        //验证原密码是否正确
        Account user = accountMapper.selectByPrimaryKey(account.getId());
        BCryptPasswordEncoder encoder = SpringUtil.getBean(BCryptPasswordEncoder.class);
        if (encoder.matches(account.getOldPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(account.getNewPassword()));
            int updateCount = accountMapper.updateByPrimaryKeySelective(user);
            return CommonResult.success(user,"修改成功！");
        }
        return CommonResult.failed("旧密码与新密码不一致");
    }

    @Override
    public CommonResult<Account> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountUser accountUser = (AccountUser)authentication.getPrincipal();
        String username = accountUser.getUsername();
        redisCache.delAdmin(username);
        return CommonResult.success(null,"注销成功！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommonResult<Account> create(Account obj){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        obj.setPassword(encoder.encode("123456"));
        Account currentAdmin = CurrentAccountUtil.getCurrentAdmin();
        obj.setUserId(currentAdmin.getId());
        int insertCount = getMapper().insert(obj);
        return CommonResult.success(obj);
    }

}
