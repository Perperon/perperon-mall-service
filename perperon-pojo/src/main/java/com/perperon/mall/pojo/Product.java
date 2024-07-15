package com.perperon.mall.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品表;
 * @author : http://www.chiner.pro
 * @date : 2024-7-12
 */

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "商品表",description = "")
@Table(name="pp_product")
public class Product implements Serializable,Cloneable{
    /** 主键标识 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "主键标识",notes = "")
    private String id ;
    /** 名称 */
    @ApiModelProperty(name = "名称",notes = "")
    private String name ;
    /** 商品类型 */
    @ApiModelProperty(name = "商品类型",notes = "")
    private String categoryId ;
    /** 状态 */
    @ApiModelProperty(name = "状态",notes = "")
    private Boolean  status ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    private String userId ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    private Date created ;
    /** 更新人 */
    @ApiModelProperty(name = "更新人",notes = "")
    private String updateBy ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    private Date update ;
}