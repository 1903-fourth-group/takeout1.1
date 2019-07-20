package com.hellojava.controller;


import com.hellojava.service.impl.OrderShoppingService;
import com.hellojava.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PayController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private OrderShoppingService orderShoppingService;
    @ResponseBody
    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public int pay(@RequestParam("userId")Integer userId, @RequestParam("totolPrice") Double totolPrice,
                   @RequestParam("orderid")String orderid){
       Double userMoney= userService.loadUserMoney(userId);
       if (userMoney>=totolPrice){
           userService.updateUserMoney(totolPrice,userId);
           orderShoppingService.updateorderstatu(orderid);
           return 1;

       }else{
           return 0;
       }
    }


}
