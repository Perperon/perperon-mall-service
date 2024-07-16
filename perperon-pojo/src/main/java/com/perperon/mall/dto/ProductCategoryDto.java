package com.perperon.mall.dto;

import com.perperon.mall.pojo.ProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author dupengcheng
 * @date 2024-07-12
 * @apiNote
 */
@Getter
@Setter
public class ProductCategoryDto extends ProductCategory {

    private String userName;

    private String updateName;

    @ApiModelProperty(value = "子级商品")
    private List<ProductCategoryDto> children;
}
