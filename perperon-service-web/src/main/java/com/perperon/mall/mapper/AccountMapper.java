package com.perperon.mall.mapper;

import com.perperon.mall.pojo.Account;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author dupengcheng
 * @date 2024/4/15
 * @apiNote
 */
public interface AccountMapper extends Mapper<Account> {

    Account selectByUsername(String username);
}
