package com.hellojava.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "commodity_info")
public class Commodity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private int comId;
    @Column(name = "com_name")
    private String comName;
    @Column(name = "com_img")
    private String comImg;
    @Column(name = "com_price")
    private double comPrice;
    @Column(name = "com_sales_permonth")
    private int comSalesPerMonth;
    @Column(name = "com_bus")
    private int comBus;
}
