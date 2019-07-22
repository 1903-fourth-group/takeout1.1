package com.hellojava.service.impl;

import com.hellojava.dao.CommodityTypeDao.CommodityTypeMapper;
import com.hellojava.entity.CommodityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityTypeServiceImpl implements CommodityTypeMapper{


    @Autowired
    private CommodityTypeMapper commodityTypeMapper;

    @Override
    public List<CommodityType> loadComTypeName() {
        List<CommodityType> commodityTypes = commodityTypeMapper.loadComTypeName();
        return commodityTypes;
    }
}
