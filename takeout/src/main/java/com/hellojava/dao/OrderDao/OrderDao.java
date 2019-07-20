package com.hellojava.dao.OrderDao;

import com.hellojava.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {
    public int insertOrder(Order shoppingOrder);
    public List<Order> loadBystatu();
    public void updateorderstatu(String id);
}
