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
 * @date 2024/5/20
 * @apiNote
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "角色",description = "")
@Table(name="pp_roles")
public class Roles implements Serializable,Cloneable{

    private static final long serialVersionUID = 1L;
    /** 唯一编号 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一编号",notes = "")
    private String id ;
    /** 编号 */
    @ApiModelProperty(name = "编号",notes = "")
    private String code ;
    /** 名字 */
    @ApiModelProperty(name = "名字",notes = "")
    private String name ;
    /** 状态 */
    @ApiModelProperty(name = "状态",notes = "")
    private Boolean status ;
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
    @Column(name = "updated_time")
    private Date updatedTime ;
}
