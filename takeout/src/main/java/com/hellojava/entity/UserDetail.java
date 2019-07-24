package com.hellojava.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_detail")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Integer detailId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "detail_name")
    private String detailName;
    @Column(name = "detail_address")
    private String detailAddress;
    @Column(name = "detail_phone")
    private String detailPhone;

}
