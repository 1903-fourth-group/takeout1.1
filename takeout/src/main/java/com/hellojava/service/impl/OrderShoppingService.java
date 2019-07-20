package com.hellojava.service.impl;

import com.hellojava.dao.OrderDao.OrderDao;
import com.hellojava.entity.Order;
import com.hellojava.service.Iordershopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderShoppingService implements Iordershopping {
    @Autowired
    private OrderDao oderDao;
    @Override
    public int insertOrder(Order shoppingOder) {
        return oderDao.insertOrder(shoppingOder) ;
    }

    @Override
    public List<Order> loadBystatu() {
        return  oderDao.loadBystatu();
    }

    @Override
    public void updateorderstatu(String id) {
        oderDao.updateorderstatu(id);
    }
}
