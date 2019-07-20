package com.hellojava.controller;


import com.hellojava.dao.BusinessTypeDao.BusinessTypeRepository;
import com.hellojava.entity.BusinessType;
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
//@RequestMapping("/businessType")
public class BackBusinessTypeController {

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    //增加二级分类
    @RequestMapping(value = "/inserttype",method = RequestMethod.POST)
    @ApiOperation(value = "增加分类信息", notes = "增加分类")
    public Integer insert(HttpServletRequest request, BusinessType businessType){
        BusinessType save = businessTypeRepository.save(businessType);
        return save!=null?1:0;
    }


    @RequestMapping(value = "/selecttype",method = RequestMethod.GET)
    @ApiOperation(value = "加载分类信息", notes = "查询分类")
    public List<BusinessType> select(Model model){
        List<BusinessType> businessList=businessTypeRepository.findAll();
        model.addAttribute("businessList", businessList);
        return businessList;
    }


    @RequestMapping(value = "/deletetype",method = RequestMethod.DELETE)
    @ApiOperation(value="根据id删除分类", notes="test: 仅1和2有正确返回")
    public Integer delete(Integer typeId){
        businessTypeRepository.deleteById(typeId);
        return 1;
    }


    @RequestMapping(value = "/updatetype",method = RequestMethod.GET)
    @ApiOperation(value = "查询分类信息", notes = "查询分类")
    public BusinessType update(Integer id, HttpServletRequest request, Model model){
        Optional<BusinessType> businessByIdList1 = businessTypeRepository.findById(id);
        BusinessType businessType=businessByIdList1.get();
        model.addAttribute("businessByIdList", businessType);
        return businessType;
    }


    @RequestMapping(value = "/update1type",method = RequestMethod.PUT)
    @ApiOperation(value = "修改分类信息", notes = "修改分类")
    public Integer update1(Model model, HttpServletRequest request, BusinessType businessType) {
        BusinessType save = businessTypeRepository.save(businessType);
        return save!=null?1:0;
    }




}
