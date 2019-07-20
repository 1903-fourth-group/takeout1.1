package com.hellojava.controller;

import com.hellojava.entity.Order;
import com.hellojava.service.impl.OrderShoppingService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/order")
public class OderController {
    @Autowired
    private OrderShoppingService orderShoppingService;

    @ResponseBody
    @RequestMapping(value = "insertorder",method = RequestMethod.POST)
    public void insertorder(@RequestBody Order shoppingOrder, HttpServletRequest request){
        String orderid = "";
        Random random = new Random();
        for (int i = 0; i <6; i++) {
            orderid += String.valueOf(random.nextInt(6));
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String date=dateFormat.format(new Date());
        shoppingOrder.setOrderTime(date);
        shoppingOrder.setOrderState(2);
        shoppingOrder.setOrderId(orderid);
        orderShoppingService.insertOrder(shoppingOrder);
        request.getSession().setAttribute("orderid",orderid);
    }

    @ResponseBody
    @RequestMapping("/state")
    @Scheduled(fixedRate = 6000)
    public void  state(){
       Long minutes=null;
        List<Order> orders=orderShoppingService.loadBystatu();
        for(int i=0;i<orders.size();i++){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
            String orderdate=orders.get(i).getOrderTime();
            try {
                Date ordertime=dateFormat.parse(orderdate);
                Date nowdate=new Date();
                long diff = nowdate.getTime() - ordertime.getTime();// 这样得到的差值是微秒级别
                minutes = diff / (1000 * 60);
                if (minutes>=1){
                  String orderid=orders.get(i).getOrderId();
                    orderShoppingService.updateorderstatu(orderid);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


    }



}
