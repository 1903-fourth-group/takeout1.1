package com.hellojava.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="com_type")
public class CommodityType implements Serializable {
    @Id
    @Column(name = "com_type_id")
    private int comTypeId;
    @Column(name = "com_type_name")
    private String comTypeName;
    @Column(name="bus_id")
    private int busId;
    @Column(name="com_id")
    private int comId;
}
