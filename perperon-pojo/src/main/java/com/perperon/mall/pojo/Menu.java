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
@ApiModel(value = "菜单",description = "")
@Table(name="pp_menu")
public class Menu implements Serializable,Cloneable{

    private static final long serialVersionUID = 1L;
    /** 主键标识 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "主键标识",notes = "")
    private String id ;
    /** 菜单名称 */
    @ApiModelProperty(name = "菜单名称",notes = "")
    @Column(name = "menu_name")
    private String menuName ;
    /** 权限标识 */
    @ApiModelProperty(name = "权限标识",notes = "")
    private String perms ;
    /** 组件路径 */
    @ApiModelProperty(name = "组件路径",notes = "")
    private String component ;
    /** 菜单图标 */
    @ApiModelProperty(name = "菜单图标",notes = "")
    private String icon ;
    /** 菜单路径 */
    @ApiModelProperty(name = "菜单路径",notes = "")
    private String path ;
    /** 菜单状态 */
    @ApiModelProperty(name = "菜单状态",notes = "")
    private Boolean status ;
    /** 菜单类型(N为目录、C为菜单、F为按钮) */
    @ApiModelProperty(name = "菜单类型(N为目录、C为菜单、F为按钮)",notes = "")
    @Column(name = "menu_type")
    private Character menuType;
    /** 父级菜单标识 */
    @ApiModelProperty(name = "父级菜单标识",notes = "")
    @Column(name = "parent_id")
    private String parentId ;
    /** 菜单等级 */
    @ApiModelProperty(name = "菜单等级",notes = "")
    private Integer level ;
    /**排序 */
    @ApiModelProperty(name = "排序",notes = "")
    private Integer sort;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    @Column(name = "user_id")
    private String userId ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date created ;
}
