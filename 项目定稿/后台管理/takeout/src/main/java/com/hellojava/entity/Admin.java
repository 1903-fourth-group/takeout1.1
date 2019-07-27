package com.hellojava.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "admin_info")
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;
    @Column(name = "admin_name")
    private String adminName;
    @Column(name = "admin_password")
    private String adminPassword;
}
