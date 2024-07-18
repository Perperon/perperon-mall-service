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
 * @date 2024-07-18
 * @apiNote
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "字典数据表",description = "")
@Table(name="pp_dictionary_data")
public class DictionaryData implements Serializable {

    /** 主键标识 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "主键标识",notes = "")
    private String id ;
    /** 字典名称 */
    @ApiModelProperty(name = "字典名称",notes = "")
    private String name ;
    /** 字典编码 */
    @ApiModelProperty(name = "字典编码",notes = "")
    private String code ;
    /** 字典类型 */
    @ApiModelProperty(name = "字典类型",notes = "")
    @Column(name="type_id")
    private String typeId ;
    /** 状态 */
    @ApiModelProperty(name = "状态",notes = "")
    private Boolean status ;
    /** 备注 */
    @ApiModelProperty(name = "备注",notes = "")
    private String remark ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    @Column(name="user_id")
    private String userId ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date created ;
    /** 更新人 */
    @ApiModelProperty(name = "更新人",notes = "")
    @Column(name="updated_by")
    private String updatedBy ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updated ;
}
