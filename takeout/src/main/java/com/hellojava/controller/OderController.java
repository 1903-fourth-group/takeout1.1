package com.hellojava.controller;

import com.google.gson.JsonObject;
import com.hellojava.dao.OrderDao.OrderDao;
import com.hellojava.entity.Order;
import com.hellojava.entity.User;
import com.hellojava.response.QueryResponseResult;
//import com.hellojava.service.impl.ComService;
import com.hellojava.service.impl.OrderShoppingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Api(tags ="订单操作")
@RequestMapping("order")
public class OderController {
    @Autowired
    private OrderShoppingService orderShoppingService;
    @Autowired
    private OrderDao orderDao;

    @ResponseBody
    @RequestMapping(value = "insertorder", method = RequestMethod.POST)
    @ApiOperation(value = "用户点击付款提交订单",notes = "所需参数：商品id(拼接字符串-,Ids),订单信息(订单order表内基本信息)")
    public void insertorder(@RequestBody Order shoppingOrder, HttpSession session) {
        System.out.println(shoppingOrder);
        String orderid = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            orderid += String.valueOf(random.nextInt(6));
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        shoppingOrder.setOrderTime(date);
        shoppingOrder.setOrderState(2);
        shoppingOrder.setOrderId(orderid);
        orderShoppingService.insertOrder(shoppingOrder);
        String comIds=shoppingOrder.getComIds();
        String[] comIds1 = comIds.split("-");
        List<String> comids = new ArrayList<>();
        for (int i = 0; i < comIds1.length; i++) {
            comids.add(comIds1[i]);
        }
        List<String> orderids = new ArrayList<>();
        orderids.add(orderid);
        Map<String, List> map = new HashMap<>();
        map.put("comids", comids);
        map.put("orderids", orderids);

        orderDao.insertordercom(map);
        session.setAttribute("orderid",orderid);

    }

    @ApiOperation("定时器(无需测试，内部使用)")
    @Scheduled(fixedRate = 6000)
    public void state() {
        Long minutes = null;
        List<Order> orders = orderShoppingService.loadBystatu();
        for (int i = 0; i < orders.size(); i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
            String orderdate = orders.get(i).getOrderTime();
            try {
                Date ordertime = dateFormat.parse(orderdate);
                Date nowdate = new Date();
                long diff = nowdate.getTime() - ordertime.getTime();// 这样得到的差值是微秒级别
                minutes = diff / (1000 * 60);
                if (minutes > 3) {
                    String orderid = orders.get(i).getOrderId();
                    orderShoppingService.updateorderstatu(orderid);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "loadAllByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "通过用户ID查询用户所有订单",notes = "返回参数：用户所有订单信息")
    public QueryResponseResult loadAll(Integer userId) {
        return orderShoppingService.loadAll(userId);
    }

    @ResponseBody
    @RequestMapping(value = "loadorderinfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询订单详情",notes ="所需参数：订单id(orderId)"+"\n"+
            "返回参数：订单详情(商品Commodity表基本信息，订单order表基本信息，商家表business基本信息)")
    public QueryResponseResult loadorderinfo(String orderId) {
        return orderShoppingService.loadorderInfoByoId(orderId);


    }

}
