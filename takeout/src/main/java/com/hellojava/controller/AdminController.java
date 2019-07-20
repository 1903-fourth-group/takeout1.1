package com.hellojava.controller;


import com.hellojava.entity.Admin;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录",notes="test: 0代表密码错误，1代表密码成功")
    public QueryResponseResult loadByAdmin(@RequestBody Admin admin){
        //返回一个Integer类型，
        return adminService.loadByAdmin(admin);
    }

}
