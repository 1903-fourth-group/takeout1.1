package com.hellojava.service.impl;


import com.hellojava.dao.BusinessTypeDao.BusinessTypeMapper;
import com.hellojava.entity.BusinessType;
import com.hellojava.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {

    @Autowired
    private BusinessTypeMapper businessTypeMapper;

    @Override
    public List<BusinessType> loadBusTypeName() {
        List<BusinessType> businessTypes = businessTypeMapper.loadBusTypeName();
        return businessTypes;
    }

    @Override
    public List<BusinessType> loadBusTypeNameTop5() {
        List<BusinessType> businessTypesTop5 = businessTypeMapper.loadBusTypeNameTop5();
        return businessTypesTop5;
    }

    @Override
    public List<BusinessType> loadBusTypeNameSecond(Integer typePid) {
        List<BusinessType> businessTypesSecond = businessTypeMapper.loadBusTypeNameSecond(typePid);
        return businessTypesSecond;
    }

    @Override
    public List<BusinessType> loadBusTypeNameAfter5() {
        List<BusinessType> businessTypesAfter5 = businessTypeMapper.loadBusTypeNameAfter5();
        return businessTypesAfter5;
    }
}
