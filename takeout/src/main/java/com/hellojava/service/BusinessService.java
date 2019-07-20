package com.hellojava.service;


import com.hellojava.response.QueryResponseResult;


public interface BusinessService {

//    List<Business> loadAll();

     QueryResponseResult loadByTypeId(int typePid);

     QueryResponseResult loadBybusId(int busId);

     QueryResponseResult selectByBusIdEvaluation(int busId);
 }
