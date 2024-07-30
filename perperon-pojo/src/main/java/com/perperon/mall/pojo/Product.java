package com.perperon.mall.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
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
    @Column(name = "category_id")
    private String categoryId ;
    /** 商品价格 */
    @ApiModelProperty(name = "商品价格",notes = "")
    private BigDecimal price ;
    /** 商品重量 */
    @ApiModelProperty(name = "商品重量",notes = "")
    private BigDecimal weight ;
    /** 商品数量 */
    @ApiModelProperty(name = "商品数量",notes = "")
    private Integer number ;
    /** 商品图片路径 */
    @ApiModelProperty(name = "商品图片路径",notes = "")
    @Column(name = "attach_path")
    private String attachPath ;
    /** 状态 */
    @ApiModelProperty(name = "状态",notes = "")
    private Boolean  status ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
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
    private String updatedName;
    @Transient
    private String categoryName;

}