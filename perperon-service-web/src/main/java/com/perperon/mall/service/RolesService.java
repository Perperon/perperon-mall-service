package com.perperon.mall.service;

import com.perperon.mall.pojo.Roles;

import java.util.List;

/**
 * @author dupengcheng
 * @date 2024-06-18
 * @apiNote
 */
public interface RolesService extends BaseService<Roles> {

    List<String> getUserAuthority(String id);

}
