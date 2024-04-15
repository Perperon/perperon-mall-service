package com.perperon.mall.service;

import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-04-15
 * @apiNote
 */
public interface AccountService {

    List<Account> listByPage(Map<String,Object> params);
}
