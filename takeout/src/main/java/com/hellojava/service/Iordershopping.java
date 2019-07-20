package com.hellojava.service;

import com.hellojava.entity.Order;
import com.hellojava.response.QueryResponseResult;

import java.util.List;

public interface Iordershopping {
    public int insertOrder(Order shoppingOder);
    public List<Order> loadBystatu();
    public void updateorderstatu(String id);
    public QueryResponseResult loadAll(Integer userId);
}
