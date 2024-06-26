package com.perperon.mall.mapper;

import com.perperon.mall.dto.AccountDto;
import com.perperon.mall.pojo.Account;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author perperon
 * @date 2024/4/15
 * @apiNote
 */
public interface AccountMapper extends Mapper<Account> {

    Account selectByUsername(String username);

    List<AccountDto> listByPage(Map<String, Object> map);
}
