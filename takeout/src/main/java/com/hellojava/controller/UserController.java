package com.hellojava.controller;


import com.hellojava.entity.User;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Api(tags ="登录操作")
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录",notes="test(integer代表状态): 0代表密码错误，1代表密码成功;" +"\n"+
            "所需参数：用户名（userName）、密码（userPassword）")
    public QueryResponseResult loginUser(User user, HttpServletRequest request){
        QueryResponseResult oneByUser = userService.findOneByUser(user);
        User curentuser=oneByUser.getQueryResult ().getUser ();
        request.getSession ().setAttribute ("curentuser",curentuser);
        return oneByUser;
    }
    //    注册
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation(value = "注册",notes="test(integer代表状态): 0代表注册失败，1代表注册成功;"+"\n"+
            "所需参数：邮箱（userEmail）、密码（userPassword）")
    public QueryResponseResult registerUser(User user) {
        QueryResponseResult addUser = userService.addUser(user);
        return addUser;
    }
}
