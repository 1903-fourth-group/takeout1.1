package com.hellojava.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "shopping_order")
public class Order implements Serializable {
    @Id
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "order_business")
    private Integer orderBusiness;
    @Column(name = "order_address")
    private String orderAddress;
    @Column(name = "order_phone")
    private String orderPhone;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "order_time")
    private String orderTime;
    @Column(name = "order_state")
    private Integer orderState;
    @Column(name = "recive_people")
    private String recivePeople;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "order_remark")
    private String orderRemark;
    @Transient
    private String comIds;
    @Transient
    private String comtotal;

}
