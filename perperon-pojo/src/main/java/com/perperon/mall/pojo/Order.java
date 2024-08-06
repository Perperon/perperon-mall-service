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
 * @date 2024-08-06
 * @apiNote
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "订单",description = "")
@Table(name="pp_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 主键标识 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "主键标识",notes = "")
    private String id ;
    /** 订单编号 */
    @ApiModelProperty(name = "订单编号",notes = "")
    @Column(name="order_no")
    private String orderNo ;
    /** 订单价格 */
    @ApiModelProperty(name = "订单价格",notes = "")
    @Column(name="order_price")
    private Double orderPrice ;
    /** 是否付款 */
    @ApiModelProperty(name = "是否付款",notes = "")
    private Boolean status ;
    /** 是否发货 */
    @ApiModelProperty(name = "是否发货",notes = "")
    @Column(name="send_status")
    private Boolean sendStatus ;
    /** 下单人 */
    @ApiModelProperty(name = "下单人",notes = "")
    @Column(name="user_id")
    private String userId ;
    /** 下单时间 */
    @ApiModelProperty(name = "下单时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date created ;
    /** 更新订单时间 */
    @ApiModelProperty(name = "更新订单时间",notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updated ;
    /** 更新订单人 */
    @ApiModelProperty(name = "更新订单人",notes = "")
    @Column(name="updated_by")
    private String updatedBy ;

    @Transient
    private String userName;
    @Transient
    private String updatedName;
}
