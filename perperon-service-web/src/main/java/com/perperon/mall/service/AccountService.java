package com.perperon.mall.service;

import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.pojo.Account;
import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-04-15
 * @apiNote
 */
public interface AccountService {
    CommonResult<List<Account>> listByPage(Map<String,Object> params);
    CommonResult<Account> create(Account account);
    CommonResult<Account> update(Account account);
    CommonResult<Account> delete(String id);
    CommonResult<Account> login(Account account);
}
