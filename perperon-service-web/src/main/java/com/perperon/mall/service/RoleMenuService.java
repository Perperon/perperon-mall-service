package com.perperon.mall.service;

import com.perperon.mall.common.response.CommonResult;
import com.perperon.mall.pojo.RoleMenu;
import java.util.Map;

/**
 * @author dupengcheng
 * @date 2024-06-18
 * @apiNote
 */
public interface RoleMenuService extends BaseService<RoleMenu> {

    CommonResult<RoleMenu> deleteByRole(Map<String, Object> params);


}
