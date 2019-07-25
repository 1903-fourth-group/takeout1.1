package com.hellojava.service.impl;

import com.hellojava.dao.BusinessDao.BusinessRepository;
import com.hellojava.dao.BusinessDao.CommodityRepository;
import com.hellojava.dao.OrderDao.OrderDao;
import com.hellojava.entity.Business;
import com.hellojava.entity.Commodity;
import com.hellojava.entity.Order;
import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
import com.hellojava.service.Iordershopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderShoppingService implements Iordershopping {
    @Autowired
    private OrderDao oderDao;
    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Override
    public int insertOrder(Order shoppingOder)
    {
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

    @Override
    public QueryResponseResult loadAll(Integer userId) {

        List<String> orderId=oderDao.loadOidByUid(userId);
        QueryResult Orders=new QueryResult();

        List allOrder=new ArrayList();
        for(int i=0;i<orderId.size();i++){
            Order order=oderDao.loadorderInfoById(orderId.get(i));
            List<Commodity> commodities=commodityRepository.findByorderId(orderId.get(i));
            Business business=businessRepository.findByorderId(orderId.get(i));
            List orders=new ArrayList();
            orders.add(order);
            orders.add(commodities);
            orders.add(business);
            allOrder.add(orders);
        }
        Orders.setList(allOrder);
        return new QueryResponseResult<>(CommonCode.SUCCESS,Orders);





    }

    @Override
    public QueryResponseResult loadorderInfoByoId(String orderId) {
       Order order=oderDao.loadorderInfoById(orderId);
        QueryResult orderResult=new QueryResult();
        orderResult.setOrder(order);
        List<Commodity> commodities=commodityRepository.findByorderId(orderId);
        orderResult.setList(commodities);
        Business business=businessRepository.findByorderId(orderId);
        orderResult.setBusiness(business);
        return new QueryResponseResult<>(CommonCode.SUCCESS,orderResult);



    }
    @Override
    public int loadtotalbycomid(Integer comId,Integer orderId) {
        return oderDao.loadtotalbycomid(comId,orderId);
    }
}



