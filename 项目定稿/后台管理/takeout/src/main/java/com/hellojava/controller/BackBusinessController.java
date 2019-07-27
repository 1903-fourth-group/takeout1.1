package com.hellojava.controller;


import com.hellojava.dao.BusinessDao.BusinessRepository;
import com.hellojava.dao.CommodityDao.CommodityReposity;
import com.hellojava.dao.CommodityDao.CommodityTypeReposity;
import com.hellojava.entity.Business;
import com.hellojava.entity.BusinessType;
import com.hellojava.service.impl.BusinessServiceImpl;
import com.hellojava.service.impl.BusinessTypeServiceImpl;
import com.hellojava.utils.LocationController;
import com.hellojava.utils.UploadPic;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BackBusinessController {
    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private BusinessTypeServiceImpl businessTypeService;

    @Autowired
    private BusinessServiceImpl businessService;

    @Autowired
    private UploadPic uploadPic;

    @Autowired
    private CommodityReposity commodityReposity;

    @Autowired
    private CommodityTypeReposity commodityTypeReposity;

    //增加商家信息
    @RequestMapping(value = "/insertbusiness", method = RequestMethod.GET)
    @ApiOperation(value = "增加商家信息", notes = "增加商家")
    public String insert(Model model) {
        List<BusinessType> businessTypesAfter5 = businessTypeService.loadBusTypeNameAfter5 ();
        model.addAttribute ("busTypesAfter5" , businessTypesAfter5);
        return "add_business";
    }


    //加载商家信息，查询所有；
    @RequestMapping(value = "/selectbusiness", method = RequestMethod.GET)
    @ApiOperation(value = "加载商家信息", notes = "查询商家")
    public String select(@RequestParam(required = false, defaultValue = "1") int page ,
                         @RequestParam(required = false, defaultValue = "10") int rows , Model model) {
        int maxPage = businessService.calcMaxPage (rows);
        if (page < 1) {
            page = maxPage;
        }
        if (page > maxPage) {
            page = 1;
        }
        List<Business> businessList = businessService.loadBusName (page , rows);
        System.out.println (businessList);
        model.addAttribute ("businessList" , businessList);
        model.addAttribute ("currentPage" , page);
        model.addAttribute ("maxPage" , maxPage);
        return "business";
    }


    //删除商家信息
    @RequestMapping(value = "/deletebusiness", method = RequestMethod.GET)
    @ApiOperation(value = "根据id删除商家", notes = "test: 仅1和2有正确返回")
    public String delete(Integer busId,Model model) {
        //commodityReposity.deleteCommoditiesByComBus (busId);
        //commodityTypeReposity.deleteCommodityTypesByBusId (busId);
        businessRepository.deleteById (busId);
        return this.select (1,10,model);
    }


    //修改商家信息，先查询1；
    @RequestMapping(value = "/updatebusiness", method = RequestMethod.GET)
    @ApiOperation(value = "查询商家信息", notes = "查询商家")
    public String update(Integer busId , Model model) {
        Business business = businessRepository.findByBusId (busId);
        List<BusinessType> businessTypesAfter5 = businessTypeService.loadBusTypeNameAfter5 ();
        model.addAttribute ("busTypesAfter5" , businessTypesAfter5);
        model.addAttribute ("business" , business);
        return "edit_business";
    }


    //保存商家信息
    @RequestMapping(value = "/update1business", method = RequestMethod.POST)
    @ApiOperation(value = "保存商家信息", notes = "保存商家")
    public String update1(Model model , Business business) {
        String pic = uploadPic.getPic (business.getMultipartFile ());
        List<String> convert = LocationController.Convert (business.getBusAddress ());
        if (convert != null) {
            business.setBusPosition (convert.get (1));
            business.setBusAddress (convert.get (0));
        }
        business.setBusImg (pic);
        Business save = businessRepository.save (business);
        System.out.println (business);
        return "redirect:selectbusiness";
    }

    //修改商家信息，后保存2；
    @RequestMapping(value = "/update2business", method = RequestMethod.POST)
    @ApiOperation(value = "编辑商家信息", notes = "编辑商家")
    public String update2(Model model , Business business) {
        if (business.getMultipartFile ().getSize () == 0) {
            Business save = businessRepository.save (business);
        } else {
            String pic = uploadPic.getPic (business.getMultipartFile ());
            List<String> convert = LocationController.Convert (business.getBusAddress ());
            business.setBusPosition (convert.get (1));
            business.setBusAddress (convert.get (0));
            business.setBusImg (pic);
            Business save = businessRepository.save (business);
        }
        return "redirect:selectbusiness";
    }


}
