package com.hellojava.dao.OrderDao;

import com.hellojava.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    // 通过商家Id查询该商家的所有订单
    @Query(value = "select * from shopping_order where order_busniess=?1",nativeQuery = true)
    List<Order> selectBybusId(int busId);

}
