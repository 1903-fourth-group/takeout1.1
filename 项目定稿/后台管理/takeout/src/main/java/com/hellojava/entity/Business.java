package com.hellojava.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "business_info")
public class Business implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private int busId;
    @Column(name = "bus_name")
    private String busName;
    @Column(name = "bus_img")
    private String busImg;
    @Column(name = "bus_address")
    private String busAddress;
    @Column(name = "bus_sales_permonth")
    private double busSalesPerMonth;
    @Column(name = "min_delivery")
    private double minDelivery;
    @Column(name = "delivery_cost_day")
    private double deliveryCostDay;
    @Column(name = "delivery_cost_night")
    private double deliveryCostNight;
    @Column(name = "consume_per_head")
    private double consumePerHead;
    private int debit;
    private double distance;
    @Column(name = "delivery_time")
    private double deliveryTime;
    @Column(name = "bus_bustype")
    private double bussType;
    @Column(name = "bus_balance")
    private double busBalance;
    @Column(name = "bus_grade")
    private double busGrade;
    @Column(name = "bus_discount")
    private String busDiscount;
    @Column(name = "bus_notice")
    private String busNotice;
    @Column(name = "insurance")
    private Integer insurance;
    @Column(name = "bus_position")
    private String busPosition;
    @Transient
    private MultipartFile multipartFile;

}
