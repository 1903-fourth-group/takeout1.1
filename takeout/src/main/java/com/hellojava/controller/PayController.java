package com.hellojava.controller;


import com.hellojava.entity.User;
import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
import com.hellojava.service.BalanceService;
import com.hellojava.service.impl.OrderShoppingService;
import com.hellojava.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Api(tags = "支付操作")
public class PayController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private OrderShoppingService orderShoppingService;
    @Autowired
    private BalanceService balanceService;


    @ResponseBody
    @ApiOperation(value = "支付密码", notes = "所需参数：用户id(userId),用户密码()" +
            "\n" + "返回参数：0代表密码错误，1代表密码正确")
    @RequestMapping(value = "/loadByPwd", method = RequestMethod.GET)
    public QueryResponseResult LoadByPwd(User user) {
        return userService.loadByPwd(user);
    }


    @ResponseBody
    @ApiOperation(value = "交易操作", notes = "所需参数：用户id(userId),商品总价(totolPrice),订单id(orderid),商家id(busId)" +
            "\n" + "返回参数：0代表支付失败，1代表支付成功")
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public QueryResponseResult pay(Integer userId, Double totolPrice,
                                   Integer busId,
                                   String oId, HttpSession session) {
        Double userMoney = userService.loadUserMoney(userId);
        String orderid = (String) session.getAttribute("orderid");
        QueryResult queryResult = new QueryResult();
        Integer result = 0;
        if (userMoney >= totolPrice) {
            userService.updateUserMoney(totolPrice, userId);
            if (oId == null) {
                orderShoppingService.updateorderstatu(orderid);
            } else {
                orderShoppingService.updateorderstatu(oId);
            }
            balanceService.updateBalance(busId, totolPrice);
            result = 1;
        } else {
            result = 0;
        }
        queryResult.setInteger(result);
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }
}
