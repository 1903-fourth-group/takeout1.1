package com.hellojava.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    @Column(name="com_type")
    private int comType;
    @Column(name = "com_taste")
    private String comTaste;
    @Column(name="discount_ticket")
    private String discountTicket;
    @Transient
    private Integer comTotal;
    @Column(name="com_mount")
    private Integer comMount;
    @Transient
    private MultipartFile pic;
}
