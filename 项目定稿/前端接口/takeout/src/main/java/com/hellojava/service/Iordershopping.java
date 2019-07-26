package com.hellojava.service;

import com.hellojava.entity.Order;
import com.hellojava.response.QueryResponseResult;

import java.util.List;
import java.util.Map;

public interface Iordershopping {
    public int insertOrder(Order shoppingOder);
    public List<Order> loadBystatu();
    public void updateorderstatu(String id);
    QueryResponseResult loadAll(Integer userId);
    QueryResponseResult loadorderInfoByoId(String orderId);
    public int loadtotalbycomid(Integer comId,Integer orderId);

}
