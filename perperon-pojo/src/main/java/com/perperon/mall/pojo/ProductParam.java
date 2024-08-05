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
 * @date 2024-07-31
 * @apiNote 商品参数表
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "商品参数表",description = "")
@Table(name="pp_product_param")
public class ProductParam implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 主键标识 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "主键标识",notes = "")
    private String id ;
    /** 分类参数id标识 */
    @ApiModelProperty(name = "分类参数id标识",notes = "")
    @Column(name="category_param_id")
    private String categoryParamId ;
    /** 商品id标识 */
    @ApiModelProperty(name = "商品id标识",notes = "")
    @Column(name="product_id")
    private String productId ;
    /** 商品参数 */
    @ApiModelProperty(name = "商品参数",notes = "")
    @Column(name="attr_value")
    private String attrValue ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    @Column(name="user_id")
    private String userId ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date  created ;
    /** 更新人 */
    @ApiModelProperty(name = "更新人",notes = "")
    @Column(name="updated_by")
    private String updatedBy ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updated ;

    @Transient
    private String typeCode;
    @Transient
    private String name;
}
