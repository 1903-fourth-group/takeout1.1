package com.hellojava.controller;


import com.hellojava.dao.CommodityDao.CommodityReposity;
import com.hellojava.dao.CommodityTypeDao.CommodityTypeRepository;
import com.hellojava.entity.BusinessType;
import com.hellojava.entity.Commodity;
import com.hellojava.entity.CommodityType;
import com.hellojava.service.impl.CommodityTypeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@Api(tags ="商品信息管理")
@ResponseBody
public class BackCommodityController {


    @Autowired
    private CommodityReposity commodityReposity;

    @Autowired
    private CommodityTypeServiceImpl commodityTypeService;

    @Autowired
    private CommodityTypeRepository commodityTypeRepository;


    //增加商品信息
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ApiOperation(value = "增加商品信息", notes = "增加商品")
    public Integer insert(HttpServletRequest request,@RequestBody @ApiParam Commodity commodity){
        Commodity save = commodityReposity.save(commodity);
        //给商品分类表中添加数据,走jpa
        String comTypeName=request.getParameter("comTypeName");
        String busId=request.getParameter("busId");
        String comId=request.getParameter("comId");
        Integer b=Integer.getInteger(busId);
        Integer c=Integer.getInteger(comId);
        CommodityType commodityType=new CommodityType();
        commodityType.setComTypeName(comTypeName);
        commodityType.setBusId(b);
        commodityType.setComId(c);
        commodityTypeRepository.save(commodityType);
        return save!=null?1:0;
    }


    //加载商品信息，查询所有；
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    @ApiOperation(value = "加载商品信息", notes = "查询所有")
    public List<Commodity> select(Model model){
        List<Commodity> commodityList=commodityReposity.findAll();
        List<CommodityType> commodityTypes = commodityTypeService.loadComTypeName();
        model.addAttribute("commodityLis", commodityList);
        //商品的分类信息
        model.addAttribute("commodityTypes",commodityTypes);
        return commodityList;
    }

    //删除商品信息
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ApiOperation(value="根据id删除商品", notes="test: 仅1和2有正确返回")
    public Integer delete(Integer comId,HttpServletRequest request){
        commodityReposity.deleteById(comId);
        //删除商品分类表中的数据
        String comType=request.getParameter("comType");
        Integer comType1=Integer.getInteger(comType);
        commodityTypeRepository.deleteById(comType1);
        return 1;
    }


    //修改商品信息，先查询1；
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    @ApiOperation(value = "查询商品信息", notes = "查询商品")
    public Commodity update(Integer comId, HttpServletRequest request, Model model){
        Optional<Commodity> optionalCommodity=commodityReposity.findById(comId);
        Commodity commodity = optionalCommodity.get();
        model.addAttribute("commodity", commodity);
        //通过商品分类编号查询商品分类的名称
        String comId1=request.getParameter("comId");
        Integer comId2=Integer.getInteger(comId1);
        List<BusinessType> byComId = commodityTypeRepository.findByComId(comId2);
        model.addAttribute("byComId",byComId);
        return commodity;
    }


    //修改商品信息，后保存2；
    @RequestMapping(value = "/update1",method = RequestMethod.PUT)
    @ApiOperation(value = "修改商品信息", notes = "修改商品")
    public Integer update1(Model model, HttpServletRequest request, Commodity commodity) {
        Commodity save = commodityReposity.save(commodity);
        //保存商品分类的信息
        String comTypeName=request.getParameter("comTypeName");
        CommodityType commodityType=new CommodityType();
        commodityType.setComTypeName(comTypeName);
        commodityTypeRepository.save(commodityType);
        return save!=null?1:0;
    }

    //模糊查询
    @RequestMapping(value = "/findByNameLike",method = RequestMethod.GET)
    @ApiOperation(value = "模糊查询", notes = "模糊查询")
    public List<Commodity> findByNameLike(String comName) {
        // 一定要加 "%"+参数名+"%"
        return commodityReposity.findByComNameLike("%"+comName+"%");
    }







}
