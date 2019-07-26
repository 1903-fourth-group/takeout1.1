package com.hellojava.service;

import com.hellojava.entity.BusinessType;

import java.util.List;

public interface BusinessTypeService {

    List<BusinessType> loadBusTypeName();

    List<BusinessType> loadBusTypeNameTop5();

    //查询二级分类的信息
    List<BusinessType> loadBusTypeNameSecond(Integer typeId);
}
