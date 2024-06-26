package com.perperon.mall.mapper;

import com.perperon.mall.pojo.AccountRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author dupengcheng
 * @date 2024-06-26
 * @apiNote
 */

public interface AccountRoleMapper extends Mapper<AccountRole> {

    int saveList(List<AccountRole> list);

}
