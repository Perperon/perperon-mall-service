package com.perperon.mall.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author dupengcheng
 * @date 2024-07-12
 * @apiNote
 */

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "商品分类",description = "")
@Table(name="pp_product_category")
public class ProductCategory implements Serializable,Cloneable{

    /** 主键标识 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "主键标识",notes = "")
    private String id ;
    /** 商品分类名称 */
    @ApiModelProperty(name = "商品分类名称",notes = "")
    private String name ;
    /** 分类标识 */
    @ApiModelProperty(name = "分类标识",notes = "")
    private String icon ;
    /** 状态 */
    @ApiModelProperty(name = "状态",notes = "")
    private Boolean status ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    @Column(name = "user_id")
    private String userId ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date created ;
    /** 更新人 */
    @ApiModelProperty(name = "更新人",notes = "")
    @Column(name = "updated_by")
    private String updatedBy ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updated ;

    @Transient
    private String userName;
    @Transient
    private String updateName;
}
