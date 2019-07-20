package com.hellojava.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "evaluation")
public class Evaluation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eva_id")
    private int evaId;
    @Column(name = "eva_order")
    private String evaOrder;
    @Column(name = "eva_detail")
    private String evaDetail;
    @Column(name = "eva_grade")
    private int evaGrade;
    @Column(name = "eva_img")
    private String evaImg;

}
