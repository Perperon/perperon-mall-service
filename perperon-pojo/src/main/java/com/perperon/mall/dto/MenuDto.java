package com.perperon.mall.dto;

import com.perperon.mall.pojo.Menu;
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
public class MenuDto extends Menu {

    @ApiModelProperty(value = "子级菜单")
    private List<MenuDto> children;
}
