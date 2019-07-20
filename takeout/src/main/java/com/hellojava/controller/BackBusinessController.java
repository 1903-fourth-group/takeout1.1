package com.hellojava.controller;


import com.hellojava.dao.BusinessDao.BusinessRepository;
import com.hellojava.entity.Business;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class BackBusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    //增加商家信息
    @RequestMapping(value = "/insertbusiness",method = RequestMethod.POST)
    @ApiOperation(value = "增加商家信息", notes = "增加商家")
    public Integer insert(HttpServletRequest request, Business business){
        Business save = businessRepository.save(business);
        return save!=null?1:0;
    }


    //加载商家信息，查询所有；
    @RequestMapping(value = "/selectbusiness",method = RequestMethod.GET)
    @ApiOperation(value = "加载商家信息", notes = "查询商家")
    public List<Business> select(Model model){
        List<Business> businessList=businessRepository.findAll();
        model.addAttribute("userList", businessList);
        return businessList;
    }


    //删除商家信息
    @RequestMapping(value = "/deletebusiness",method = RequestMethod.DELETE)
    @ApiOperation(value="根据id删除商家", notes="test: 仅1和2有正确返回")
    public Integer delete(Integer busId){
        businessRepository.deleteById(busId);
        return 1;
    }


    //修改商家信息，先查询1；
    @RequestMapping(value = "/updatebusiness",method = RequestMethod.GET)
    @ApiOperation(value = "查询商家信息", notes = "查询商家")
    public Business update(Integer id, HttpServletRequest request, Model model){
        Optional<Business> businessByIdList1=businessRepository.findById(id);
        Business business=businessByIdList1.get();
        model.addAttribute("businessByIdList", business);
        return business;
    }


    //修改商家信息，后保存2；
    @RequestMapping(value = "/update1business",method = RequestMethod.PUT)
    @ApiOperation(value = "修改商家信息", notes = "修改商家")
    public Integer update1(Model model, HttpServletRequest request, Business business) {
        Business save = businessRepository.save(business);
        return save!=null?1:0;
    }









}
