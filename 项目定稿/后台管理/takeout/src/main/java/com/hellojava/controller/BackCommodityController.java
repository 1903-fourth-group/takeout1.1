package com.hellojava.controller;


import com.hellojava.dao.CommodityDao.CommodityReposity;
import com.hellojava.dao.CommodityTypeDao.CommodityTypeRepository;
import com.hellojava.entity.Commodity;
import com.hellojava.service.impl.CommodityTypeServiceImpl;
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

@Controller
@Api(tags = "商品信息管理")
public class BackCommodityController {

    @Autowired
    private UploadPic uploadPic;

    @Autowired
    private CommodityReposity commodityReposity;

    @Autowired
    private CommodityTypeServiceImpl commodityTypeService;

    @Autowired
    private CommodityTypeRepository commodityTypeRepository;


    //增加商品信息
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ApiOperation(value = "增加商品信息", notes = "增加商品")
    public String insert(Commodity commodity , Model model) {
        model.addAttribute (commodity);
        return "add_commodity";
    }


    //加载商品信息，查询所有；
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ApiOperation(value = "加载商品信息", notes = "查询所有")
    public String select(Model model , Commodity commodity) {
        List<Commodity> commodityList = commodityReposity.findByComType (commodity.getComType ());
        model.addAttribute ("commodityList" , commodityList);
        model.addAttribute (commodity);
        return "commodity";
    }

    //删除商品信息
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "根据id删除商品", notes = "test: 仅1和2有正确返回")
    public String delete(Commodity commodity , Model model) {
        commodityReposity.deleteById (commodity.getComId ());
        return this.select (model , commodity);
    }


    //修改商品信息，先查询1；
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ApiOperation(value = "查询商品信息", notes = "查询商品")
    public String update(Integer comId , Model model) {
        Optional<Commodity> optionalCommodity = commodityReposity.findById (comId);
        Commodity commodity = optionalCommodity.get ();
        model.addAttribute ("commodity" , commodity);

        return "edit_commodity";
    }


    //修改商品信息，后保存2；
    @RequestMapping(value = "/update1", method = RequestMethod.POST)
    @ApiOperation(value = "修改商品信息", notes = "修改商品")
    public String update1(Model model , Commodity commodity) {
        if (commodity.getMultipartFile ().getSize () == 0) {
            Commodity save = commodityReposity.save (commodity);
        } else {
            String pic = uploadPic.getPic (commodity.getMultipartFile ());
            commodity.setComImg (pic);
            Commodity save = commodityReposity.save (commodity);
        }
        return this.select (model , commodity);
    }

    //模糊查询
//    @RequestMapping(value = "/findByNameLike", method = RequestMethod.GET)
//    @ApiOperation(value = "模糊查询", notes = "模糊查询")
//    public List<Commodity> findByNameLike(String comName) {
//        // 一定要加 "%"+参数名+"%"
//        return commodityReposity.findByComNameLike ("%" + comName + "%");
//    }


}
