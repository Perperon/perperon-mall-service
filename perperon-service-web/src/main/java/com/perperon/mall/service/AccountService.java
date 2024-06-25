package com.perperon.mall.service;

import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.pojo.Account;

/**
 * @author perperon
 * @date 2024-04-15
 * @apiNote
 */
public interface AccountService extends BaseService<Account> {
    CommonResult<Account> login(Account account);

    CommonResult<Account> logout();

    CommonResult<Account> updatePwd(Account account);
}
