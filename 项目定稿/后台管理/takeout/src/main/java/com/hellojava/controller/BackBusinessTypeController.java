package com.hellojava.controller;


import com.hellojava.dao.BusinessTypeDao.BusinessTypeRepository;
import com.hellojava.entity.BusinessType;
import com.hellojava.service.impl.BusinessTypeServiceImpl;
import com.hellojava.utils.UploadPic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Api(tags = "商家分类管理")
@Controller
public class BackBusinessTypeController {

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    @Autowired
    private BusinessTypeServiceImpl businessTypeService;

    @Autowired
    private UploadPic uploadPic;

    //在商家分类表中查询前5条数据，五大类
    @RequestMapping(value = "/selectcategory", method = RequestMethod.GET)
    @ApiOperation(value = "加载五大分类信息", notes = "加载分类")
    public String selectCategory(Model model) {
        List<BusinessType> businessCategoryList = businessTypeService.loadBusTypeNameTop5 ();
        model.addAttribute ("businessCategoryList" , businessCategoryList);
        return "category";
    }

    //通过一级分类Id来查询相应的二级分类表
    @RequestMapping(value = "/selecttype", method = RequestMethod.GET)
    @ApiOperation(value = "通过一级分类Id来查询相应的二级分类表", notes = "查询分类")
    public String select(Model model , Integer typeId) {
        List<BusinessType> businessTypeList = businessTypeService.loadBusTypeNameSecond (typeId);
        System.out.println (businessTypeList);
        model.addAttribute ("businessTypeList" , businessTypeList);
        model.addAttribute ("typeId" , typeId);
        return "type";
    }


    //增加二级分类,需要把商家的ID添加进去
    @RequestMapping(value = "/inserttype", method = RequestMethod.GET)
    @ApiOperation(value = "增加分类信息", notes = "增加分类")
    public String insert(Model model , Integer typePid) {
        model.addAttribute ("pid" , typePid);
        return "add_type";
    }


    @RequestMapping(value = "/deletetype", method = RequestMethod.GET)
    @ApiOperation(value = "根据id删除分类", notes = "test: 仅1和2有正确返回")
    public String delete(BusinessType businessType , Model model) {
        businessTypeRepository.deleteById (businessType.getTypeId ());
        System.out.println (businessType);
        return this.select (model , businessType.getTypePid ());
    }


    @RequestMapping(value = "/updatetype", method = RequestMethod.GET)
    @ApiOperation(value = "查询分类信息", notes = "查询分类")
    public String update(Integer typeId , Model model) {
        Optional<BusinessType> businessByIdList1 = businessTypeRepository.findById (typeId);
        BusinessType businessType = businessByIdList1.get ();
        model.addAttribute ("businessType" , businessType);
        return "edit_type";
    }


    @RequestMapping(value = "/update1type", method = RequestMethod.POST)
    @ApiOperation(value = "修改分类信息", notes = "修改分类")
    public String update1(Model model , BusinessType businessType) {
        String pic = uploadPic.getPic (businessType.getMultipartFile ());
        businessType.setTypeImg (pic);
        BusinessType save = businessTypeRepository.save (businessType);
        return this.select (model , businessType.getTypePid ());
    }


}
