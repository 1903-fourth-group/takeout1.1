package com.hellojava.service;


import com.hellojava.entity.Business;
import com.hellojava.response.QueryResponseResult;

import java.util.List;


public interface BusinessService {

     QueryResponseResult selectBusinessAll(int busType);

     QueryResponseResult selectBusinessTypeAll(String busTypeName);

    QueryResponseResult selectBusinessAll(String busTypeName);

     QueryResponseResult loadBybusId(int busId);

     QueryResponseResult selectByBusIdEvaluation(int busId);

     QueryResponseResult selectByUserIdCollection(int userId);

     int addUserIdCollection(int userId, int busId);

     int delectUserIdCollection(int userId, int busId);


    public List<Business> loadBusName(int page,int rows);

    //分页
    public Integer calcMaxPage(int rows);

 }
