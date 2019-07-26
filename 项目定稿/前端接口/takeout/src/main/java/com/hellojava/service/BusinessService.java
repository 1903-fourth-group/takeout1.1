package com.hellojava.service;


import com.hellojava.response.QueryResponseResult;


public interface BusinessService {

     QueryResponseResult selectBusinessAll(int busType);

     QueryResponseResult selectBusinessTypeAll(String busTypeName);

    QueryResponseResult selectBusinessAll(String busTypeName);

     QueryResponseResult loadBybusId(int busId);

     QueryResponseResult selectByBusIdEvaluation(int busId);

     QueryResponseResult selectByUserIdCollection(int userId);

     int addUserIdCollection(int userId, int busId);

     int delectUserIdCollection(int userId, int busId);
 }
