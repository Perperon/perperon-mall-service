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
 * @date 2024/12/5
 * @apiNote
 */
@ApiModel(value = "订单商品信息表",description = "")
@Table(name="pp_order_item")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "主键标识", notes = "")
    private String id;
    /**
     * 订单标识
     */
    @ApiModelProperty(name = "订单标识", notes = "")
    @Column(name="order_id")
    private String orderId;
    /**
     * 商品标识
     */
    @ApiModelProperty(name = "商品标识", notes = "")
    @Column(name="product_id")
    private String productId;
    /**
     * 数量
     */
    @ApiModelProperty(name = "数量", notes = "")
    @Column(name="product_quantity")
    private Integer productQuantity;
    /**
     * 创建人
     */
    @ApiModelProperty(name = "创建人", notes = "")
    @Column(name="user_id")
    private String userId;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间", notes = "")
    private Date created;
}
