package com.perperon.mall.dto;

import com.perperon.mall.pojo.Account;
import com.perperon.mall.pojo.Roles;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author perperon
 * @date 2024/6/3
 * @apiNote
 */
@Getter
@Setter
public class AccountDto extends Account {

    @ApiModelProperty(value = "用户角色")
    private List<Roles> roleList;
}
