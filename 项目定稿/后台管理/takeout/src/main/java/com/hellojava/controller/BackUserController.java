package com.hellojava.controller;

import com.hellojava.entity.User;
import com.hellojava.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Api(tags = "后台用户操作")
public class BackUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/selectUser", method = RequestMethod.GET)
    @ApiOperation(value = "加载用户信息", notes = "加载用户")
    public String selectUser(@RequestParam(required = false, defaultValue = "1") int page ,
                             @RequestParam(required = false, defaultValue = "10") int rows , Model model) {
        int maxPage = userService.getMaxCount (rows);
        if (page < 1) {
            page = maxPage;
        }
        if (page > maxPage) {
            page = 1;
        }
        List<User> userList = userService.findAll (page , rows);
        model.addAttribute ("currentPage" , page);
        model.addAttribute ("maxPage" , maxPage);
        model.addAttribute ("userList" , userList);
        return "user";
    }

    //    method = RequestMethod.GET
    //模糊查询
    @RequestMapping(value = "/findUser")
    @ApiOperation(value = "加载用户信息", notes = "加载用户")
    public String findUserLikely(@RequestParam String userName , Model model) {
        model.addAttribute ("currentPage" , 1);
        model.addAttribute ("maxPage" , 1);
        List<User> userList = userService.findUserLikely ("%" + userName + "%");
        model.addAttribute ("userList" , userList);
        model.addAttribute("userName",userName);
        return "user";
    }
}
