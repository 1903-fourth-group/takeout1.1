package com.hellojava.controller;


import com.hellojava.entity.UserDetail;
import com.hellojava.response.QueryResponseResult;
import com.hellojava.service.UserDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userDetail")
@Api(tags ="收货人信息操作")
@ResponseBody
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @RequestMapping(value = "/saveUserDetail",method = RequestMethod.POST)
    @ApiOperation(value = "添加收货人信息",notes="所需参数：用户明细表userDetail基本内容")
    public void SaveUserDetail(@RequestBody UserDetail userDetail){
        userDetailService.saveUserDetail(userDetail);
    }

    @RequestMapping(value = "/queryUserDetail",method = RequestMethod.POST)
    @ApiOperation(value = "查询收货人信息",notes="所需参数：用户id(userId)")
    public QueryResponseResult QueryUserDetail(Integer userId){
       return userDetailService.loadAllUserDetail(userId);
    }
}
