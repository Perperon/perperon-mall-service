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
 * @author perperon
 * @date 2024/7/30
 * @apiNote
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel("商品附件")
@Table(name = "pp_product_attach")
public class ProductAttach implements Serializable {
    /** 主键标识 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "主键标识",notes = "")
    private String id ;
    /** 商品id标识 */
    @ApiModelProperty(name = "商品id标识",notes = "")
    @Column(name = "product_id")
    private String productId ;
    /** 附件名称 */
    @ApiModelProperty(name = "附件名称",notes = "")
    private String name ;
    /** 附件路径 */
    @ApiModelProperty(name = "附件路径",notes = "")
    private String path ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date created ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    @Column(name = "user_id")
    private String userId ;
    /** 更新人 */
    @ApiModelProperty(name = "更新人",notes = "")
    @Column(name = "updated_by")
    private String updatedBy ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updated ;
}
