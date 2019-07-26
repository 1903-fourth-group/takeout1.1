package com.hellojava.dao.OrderDao;

import com.hellojava.entity.Comtotal;
import com.hellojava.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDao {
    public int insertOrder(Order shoppingOrder);
    public List<Order> loadBystatu();
    public void updateorderstatu(String id);
    public Order loadorderInfoById(String orderId);
    //通过用户ID查询其所有订单
    public List<String> loadOidByUid(Integer uid);
    //添加商品--订单映射
    public int insertordercom(List<Comtotal> comtotals);
    public Integer loadtotalbycomid(@Param("comId") Integer comId,
                                    @Param("orderid") Integer orderid);
}
