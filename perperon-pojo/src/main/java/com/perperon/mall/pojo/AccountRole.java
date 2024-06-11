package com.perperon.mall.pojo;

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
@ApiModel(value = "用户角色",description = "")
@Table(name="pp_account_role")
public class AccountRole implements Serializable,Cloneable{

    private static final long serialVersionUID = 1L;
    /** 主键标识 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "主键标识",notes = "")
    private String id ;
    /** 用户标识 */
    @ApiModelProperty(name = "用户标识",notes = "")
    @Column(name = "account_id")
    private String accountId ;
    /** 角色标识 */
    @ApiModelProperty(name = "角色标识",notes = "")
    @Column(name = "role_id")
    private String roleId ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    @Column(name = "user_id")
    private String userId ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    private Date created ;
}
