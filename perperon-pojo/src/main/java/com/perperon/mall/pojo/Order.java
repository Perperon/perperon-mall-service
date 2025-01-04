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
    @ApiModelProperty(name = "支付方式",notes = "")
    private Integer status ;
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
    /** 订单状态 */
    @ApiModelProperty(name = "订单状态",notes = "")
    @Column(name="order_status")
    private Integer orderStatus ;
    /** 订单来源 */
    @ApiModelProperty(name = "订单来源",notes = "")
    @Column(name="order_from")
    private String orderFrom ;
    /** 提交时间 */
    @ApiModelProperty(name = "提交时间",notes = "")
    @Column(name="order_date")
    private Date orderDate ;
    /** 订单类型 */
    @ApiModelProperty(name = "订单类型",notes = "")
    @Column(name="order_type")
    private String orderType ;
    /** 配送方式 */
    @ApiModelProperty(name = "配送方式",notes = "")
    @Column(name="delivery_method")
    private String deliveryMethod ;
    /** 物流单号 */
    @ApiModelProperty(name = "物流单号",notes = "")
    @Column(name="tracking_number")
    private String trackingNumber ;
    /** 自动确认收货时间 */
    @ApiModelProperty(name = "自动确认收货时间",notes = "")
    @Column(name="auto_confirm_time")
    private Date autoConfirmTime ;
    /** 订单可得优币 */
    @ApiModelProperty(name = "订单可得优币",notes = "")
    @Column(name="orders_receive_premium")
    private Integer ordersReceivePremium ;
    /** 活动信息 */
    @ApiModelProperty(name = "活动信息",notes = "")
    @Column(name="event_information")
    private String eventInformation ;
    /** 订单可得成长值 */
    @ApiModelProperty(name = "订单可得成长值",notes = "")
    @Column(name="order_generate_value")
    private Integer orderGenerateValue ;
    /** 收货人 */
    @ApiModelProperty(name = "收货人",notes = "")
    private String consignee ;
    /** 手机号码 */
    @ApiModelProperty(name = "手机号码",notes = "")
    private String phone ;
    /** 邮政编码 */
    @ApiModelProperty(name = "邮政编码",notes = "")
    @Column(name="postal_code")
    private String postalCode ;
    /** 收货地址 */
    @ApiModelProperty(name = "收货地址",notes = "")
    private String address ;
    /** 运费 */
    @ApiModelProperty(name = "运费",notes = "")
    private Double freight ;
    /** 优惠券 */
    @ApiModelProperty(name = "优惠券",notes = "")
    private Double coupon ;
    /** 积分抵扣 */
    @ApiModelProperty(name = "积分抵扣",notes = "")
    private Double deductal ;
    /** 活动优惠 */
    @ApiModelProperty(name = "活动优惠",notes = "")
    @Column(name="event_discounts")
    private Double eventDiscounts ;
    /** 折扣金额 */
    @ApiModelProperty(name = "折扣金额",notes = "")
    @Column(name="discount_amount")
    private Double discountAmount ;

    @Transient
    private String userName;
    @Transient
    private String updatedName;
}
