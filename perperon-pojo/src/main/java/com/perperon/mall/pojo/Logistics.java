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
 * @date 2024-08-14
 * @apiNote
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "物流表",description = "")
@Table(name="pp_logistics")
public class Logistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键标识 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "主键标识",notes = "")
    private String id ;
    /** 订单id标识 */
    @ApiModelProperty(name = "订单id标识",notes = "")
    @Column(name = "order_id")
    private String orderId ;
    /** 物流单号 */
    @ApiModelProperty(name = "物流单号",notes = "")
    @Column(name = "logistics_no")
    private String logisticsNo ;
    /** 物流消息内容 */
    @ApiModelProperty(name = "物流消息内容",notes = "")
    private String context ;
    /** 抵达时间 */
    @ApiModelProperty(name = "抵达时间",notes = "")
    private Date time ;
    /** 离开时间 */
    @ApiModelProperty(name = "离开时间",notes = "")
    @Column(name = "f_time")
    private Date fTime ;
    /** 状态 */
    @ApiModelProperty(name = "状态",notes = "")
    private Boolean status ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    @Column(name = "user_id")
    private String userId ;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "创建时间",notes = "")
    private Date created ;
}
