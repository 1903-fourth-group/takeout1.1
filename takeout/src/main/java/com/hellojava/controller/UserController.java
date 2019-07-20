package com.hellojava.controller;


import com.hellojava.entity.User;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ApiOperation("查询所有")
    public QueryResponseResult findAll() {
        QueryResponseResult all = userService.findAll();
        return all;
    }
//    登录
    @RequestMapping(value = "/login/{user}",method = RequestMethod.POST)
    @ApiOperation("登录")
    public QueryResponseResult loginUser(@PathVariable User user){
//        if(user.getUserName()!=null&&user.getUserPassword()!=null){
//            userService.findOneByUser(user);
//            return 1;
//        }else {
//            return 0;
//        }
        QueryResponseResult oneByUser = userService.findOneByUser(user);
        return oneByUser;
    }
//    注册
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation("注册")
    public QueryResponseResult registerUser(User user) {
//        if(user.getUserName()!=null && user.getUserPassword()!=null && user.getUserEmail()!=null){
//            userService.addUser(user);
//            return "1";
//        }else {
//            return "0";
//        }
        QueryResponseResult addUser = userService.addUser(user);
        return addUser;
    }
}
