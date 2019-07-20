package com.hellojava.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "business_type")
public class BusinessType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_type_id")
    private int typeId;
    @Column(name = "bus_type_pid")
    private int typePid;
    @Column(name = "bus_type_name")
    private String typeName;
}
