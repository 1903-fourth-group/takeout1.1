package com.hellojava.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name = "current_user")
    private String currentUser;
    @Column(name = "order_address")
    private String orderAddress;
    @Column(name = "order_phone")
    private Integer orderPhone;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "order_time")
    private String orderTime;
    @Column(name = "order_state")
    private Integer orderState;
    @Column(name = "user_id")
    private Integer userId;
}
