package com.hellojava.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "carousel_info")

public class Carousel {

    @Id
    @Column(name = "carousel_id")
    private Integer carouselId;

    @Column(name = "carousel_img")
    private String carouselImg;
}
