package com.perperon.mall.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author dupengcheng
 * @date 2024-07-15
 * @apiNote
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "日志表",description = "")
@Table(name="pp_logs")
public class Logs {
        /** 主键标识 */
        @Id
        @GeneratedValue
        @ApiModelProperty(name = "主键标识",notes = "")
        private String id ;
        /** 操作名称 */
        @ApiModelProperty(name = "操作名称",notes = "")
        private String name ;
        /** 操作类型 */
        @ApiModelProperty(name = "操作类型",notes = "")
        private String type ;
        /** 操作内容 */
        @ApiModelProperty(name = "操作内容",notes = "")
        private String content ;
        /** 创建人 */
        @ApiModelProperty(name = "创建人",notes = "")
        private String userId ;
        /** 创建时间 */
        @ApiModelProperty(name = "创建时间",notes = "")
        private Date created ;
    }
