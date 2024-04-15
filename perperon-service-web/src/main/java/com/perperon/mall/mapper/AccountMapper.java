package com.perperon.mall.mapper;

import com.perperon.mall.pojo.Account;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024/4/15
 * @apiNote
 */
public interface AccountMapper extends Mapper<Account> {

    /*List<Account> listByPage(Map<String,Object> params);
    int create(Account account);
    int update(Account account);
    int delete(String id);*/
}
