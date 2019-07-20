package com.hellojava.controller;


import com.hellojava.dao.CommodityDao.CommodityReposity;
import com.hellojava.entity.Commodity;
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
//@RequestMapping("/commodity")
public class BackCommodityController {


    @Autowired
    private CommodityReposity commodityReposity;


    //增加商品信息
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ApiOperation(value = "增加商品信息", notes = "增加商品")
    public Integer insert(HttpServletRequest request,@RequestBody @ApiParam Commodity commodity){
        Commodity save = commodityReposity.save(commodity);
        return save!=null?1:0;
    }


    //加载商品信息，查询所有；
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    @ApiOperation(value = "加载商品信息", notes = "查询所有")
    public List<Commodity> select(Model model){
        List<Commodity> commodityList=commodityReposity.findAll();
        model.addAttribute("userList", commodityList);
        return commodityList;
    }

    //删除商品信息
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ApiOperation(value="根据id删除商品", notes="test: 仅1和2有正确返回")
    public Integer delete(Integer comId){
        commodityReposity.deleteById(comId);
        return 1;
    }


    //修改商品信息，先查询1；
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    @ApiOperation(value = "查询商品信息", notes = "查询商品")
    public Commodity update(Integer comId, HttpServletRequest request, Model model){
        Optional<Commodity> optionalCommodity=commodityReposity.findById(comId);
        Commodity commodity = optionalCommodity.get();
        model.addAttribute("userByIdList", commodity);
        return commodity;
    }


    //修改商品信息，后保存2；
    @RequestMapping(value = "/update1",method = RequestMethod.PUT)
    @ApiOperation(value = "修改商品信息", notes = "修改商品")
    public Integer update1(Model model, HttpServletRequest request, Commodity commodity) {
        Commodity save = commodityReposity.save(commodity);
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
