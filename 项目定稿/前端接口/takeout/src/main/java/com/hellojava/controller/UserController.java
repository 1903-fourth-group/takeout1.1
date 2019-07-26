package com.hellojava.controller;


import com.hellojava.entity.EmailInfo;
import com.hellojava.entity.User;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.service.EmailInfoService;
import com.hellojava.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Api(tags ="登录操作")
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailInfoService emailInfoService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录",notes="test(integer代表状态): 0代表密码错误，1代表密码成功;" +"\n"+
            "所需参数：用户名（userName）、密码（userPassword）")
    public QueryResponseResult loginUser(@RequestBody User user){
        QueryResponseResult oneByUser = userService.findOneByUser(user);
        return oneByUser;
    }
    //    注册
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation(value = "邮箱验证",notes="所需参数：邮箱(emailInfo)")
    public void registerUser(@RequestBody EmailInfo emailInfo){
        emailInfoService.addEmail(emailInfo);
        emailInfoService.deleteEmailPwd(emailInfo);
    }

    @RequestMapping(value = "/EmailLogin", method = RequestMethod.POST)
    @ApiOperation(value = "邮箱注册或登录",notes="所需参数：邮箱验证表基本信息(邮箱emailName和验证码email_pwd)"+"\n"+
                    "返回参数：0代表登录失败，1代表登录成功，2代表验证码失效")
    public QueryResponseResult loginEmail(@RequestBody EmailInfo emailInfo) {
        QueryResponseResult oneEmail = emailInfoService.findOneEmail(emailInfo);
        return oneEmail;
    }

    //个人信息查询
    @RequestMapping(value = "/queryByIdUser",method = RequestMethod.POST)
    @ApiOperation(value = "查询当前用户信息",notes="所需参数：用户id(userId)" +
            "\n"+"返回参数：用户基本信息")
    public QueryResponseResult QueryByIdUser(Integer userId) {
        QueryResponseResult userByUserId = userService.findUserByUserId(userId);
        return userByUserId;
    }

    //个人信息修改
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ApiOperation(value = "修改当前用户信息",notes="所需参数：用户id(userId)" +
            "\n"+"返回参数：用户基本信息")
    public void UpdateUser(@RequestBody User user) {
        userService.editUser(user);
    }
}
