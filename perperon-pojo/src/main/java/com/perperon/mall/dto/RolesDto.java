package com.perperon.mall.dto;

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
public class RolesDto extends Roles {

    @ApiModelProperty(value = "角色权限菜单")
    private List<MenuDto> children;
}
