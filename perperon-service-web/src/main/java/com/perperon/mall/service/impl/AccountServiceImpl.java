package com.perperon.mall.service.impl;

import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.mapper.AccountMapper;
import com.perperon.mall.pojo.Account;
import com.perperon.mall.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024/4/15
 * @apiNote
 */
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    @Override
    public CommonResult<List<Account>> listByPage(Map<String, Object> params) {
        Example example = new Example(Account.class);
        example.createCriteria().andLike("username", params.get("username").toString());
        return CommonResult.success(accountMapper.selectByExample(example));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    @Override
    public CommonResult<Account> create(Account account) {
        int i = accountMapper.insert(account);
        return CommonResult.success(account);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    @Override
    public CommonResult<Account> update(Account account) {
        accountMapper.updateByPrimaryKeySelective(account);
        return CommonResult.success(account);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    @Override
    public CommonResult<Account> delete(String id) {
        accountMapper.deleteByPrimaryKey(id);
        return CommonResult.success(null,"删除成功！");
    }

    public CommonResult<Account> login(Account account) {

        return null;
    }
}
