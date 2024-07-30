package com.perperon.mall.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

        private static final long serialVersionUID = 1L;
        /** 主键标识 */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
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
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private Date created ;
    }
