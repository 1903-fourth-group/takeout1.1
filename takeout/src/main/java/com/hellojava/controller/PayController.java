package com.hellojava.controller;


import com.hellojava.response.CommonCode;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.response.QueryResult;
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

@Controller
@Api(tags = "支付操作")
public class PayController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private OrderShoppingService orderShoppingService;

    @ResponseBody
    @ApiOperation(value = "交易操作", notes = "所需参数：用户id(userId),商品总价(totolPrice),订单id(orderid)" +
            "\n" + "返回参数：0代表支付失败，1代表支付成功")
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public QueryResponseResult pay(@RequestParam("userId") Integer userId, @RequestParam("totolPrice") Double totolPrice,
                                   @RequestParam("orderid") String orderid) {
        Double userMoney = userService.loadUserMoney(userId);
        QueryResult queryResult = new QueryResult();
        Integer result = 0;
        if (userMoney >= totolPrice) {
            userService.updateUserMoney(totolPrice, userId);
            orderShoppingService.updateorderstatu(orderid);
            result = 1;
        } else {
            result = 0;
        }
        queryResult.setInteger(result);
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }


}
