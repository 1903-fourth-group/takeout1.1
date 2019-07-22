package com.hellojava.controller;


import com.hellojava.dao.BusinessTypeDao.BusinessTypeRepository;
import com.hellojava.entity.BusinessType;
import com.hellojava.service.impl.BusinessTypeServiceImpl;
import io.swagger.annotations.Api;
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

@ResponseBody
@Api(tags ="商家分类管理")
@Controller
public class BackBusinessTypeController {

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    @Autowired
    private BusinessTypeServiceImpl businessTypeService;

    //在商家分类表中查询前5条数据，五大类
    @RequestMapping(value = "/selecttop5type",method = RequestMethod.GET)
    @ApiOperation(value = "加载五大分类信息", notes = "加载分类")
    public List<BusinessType> loadBusTypeNameTop5(Model model){
        List<BusinessType> businessTop5List=businessTypeService.loadBusTypeNameTop5();
        model.addAttribute("businessTop5List", businessTop5List);
        return businessTop5List;
    }

    //通过商家Id来查询相应的二级分类表
    @RequestMapping(value = "/selecttype",method = RequestMethod.GET)
    @ApiOperation(value = "通过商家Id来查询相应的二级分类表", notes = "查询分类")
    public List<BusinessType> select(Model model, HttpServletRequest request){
        String busId = request.getParameter("busId");
        Integer busId1=Integer.getInteger(busId);
        List<BusinessType> businessAfter5List=businessTypeService.loadBusTypeNameSecond(busId1);
        model.addAttribute("businessAfter5List", businessAfter5List);
        return businessAfter5List;
    }


    //增加二级分类,需要把商家的ID添加进去
    @RequestMapping(value = "/inserttype",method = RequestMethod.POST)
    @ApiOperation(value = "增加分类信息", notes = "增加分类")
    public Integer insert(HttpServletRequest request, BusinessType businessType){
        String busId = request.getParameter("busId");
        Integer busId1=Integer.getInteger(busId);
        businessType.setTypePid(busId1);
        BusinessType save = businessTypeRepository.save(businessType);
        return save!=null?1:0;
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
        String busId = request.getParameter("busId");
        Integer busId1=Integer.getInteger(busId);
        businessType.setTypePid(busId1);
        BusinessType save = businessTypeRepository.save(businessType);
        return save!=null?1:0;
    }




}
