package com.hellojava.controller;


import com.hellojava.dao.CommodityDao.CommodityReposity;
import com.hellojava.dao.CommodityTypeDao.CommodityTypeRepository;
import com.hellojava.entity.CommodityType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@Api(tags ="商品分类信息管理")
public class BackCommodityTypeController {

    @Autowired
    private CommodityTypeRepository commodityTypeRepository;

    @Autowired
    private CommodityReposity commodityReposity;

    //增加商品分类信息
    @RequestMapping(value = "/insertComType",method = RequestMethod.POST)
    @ApiOperation(value = "增加商品分类信息", notes = "增加商品分类")
    public String insertComType(Model model, CommodityType commodityType){
        CommodityType save = commodityTypeRepository.save(commodityType);
        model.addAttribute("commodityTypeList",save);
        return this.select (model,commodityType.getBusId ());
    }

    //加载商品的分类信息，查询所有；
    @RequestMapping(value = "/selectComType",method = RequestMethod.GET)
    @ApiOperation(value = "加载商品分类信息", notes = "查询所有")
    public String select(Model model,Integer busId){
        System.out.println(busId);
        List<CommodityType> commodityTypeList = commodityTypeRepository.findByBusId (busId);
        model.addAttribute("commodityTypeList", commodityTypeList);
        model.addAttribute ("busId",busId);
        return "commodityType";
    }

    //删除商品的分类信息
    @RequestMapping(value = "/deleteComType",method = RequestMethod.GET)
    @ApiOperation(value="根据id删除商品", notes="test: 仅1和2有正确返回")
    public String delete(CommodityType commodityType,Model model){
        commodityReposity.deleteCommoditiesByComType (commodityType.getComTypeId ());
        commodityTypeRepository.deleteById(commodityType.getComTypeId ());
        return this.select (model,commodityType.getBusId ());
    }

    //修改商品的分类信息，先查询1；
    @RequestMapping(value = "/updateComType",method = RequestMethod.GET)
    @ApiOperation(value = "查询商品的分类信息", notes = "查询商品分类")
    public String update(Integer comTypeId,Model model){
        Optional<CommodityType> optionalCommodityType=commodityTypeRepository.findById(comTypeId);
        CommodityType commodityType= optionalCommodityType.get();
        model.addAttribute("commodityType", commodityType);
        return "edit_commodityType";
    }


    //修改商品的分类信息，后保存2；
    @RequestMapping(value = "/updateComType1",method = RequestMethod.POST)
    @ApiOperation(value = "修改商品分类信息", notes = "修改商品分类")
    public String update1(Model model,CommodityType commodityType) {
        CommodityType save = commodityTypeRepository.save(commodityType);
        return this.select (model,commodityType.getBusId ());
    }



}
