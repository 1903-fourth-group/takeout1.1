package com.hellojava.service;


import com.hellojava.response.QueryResponseResult;

public interface CommodityService {

    /**
     * 通过商品id查询商品的详情
     * @param comId
     * @return
     */
    QueryResponseResult loadById(int comId);

    QueryResponseResult findAllBycomBus(int comBus);
}
