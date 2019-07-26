package com.hellojava.controller;


import com.hellojava.response.QueryResponseResult;
import com.hellojava.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "商品操作")
@Controller
@RequestMapping("/commodity")
@ResponseBody
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    /**
     * 通过前台传递的商家Id 查询该商家所有的商品
     * @param comBus
     * @return
     */
    @RequestMapping(value = "/loadByTypeId",method = RequestMethod.GET)
    @ApiOperation(value = "查询该商家所有的商品",notes = "所需参数:商家id(typePid)" +
            "\n"+"返回参数：商品基本信息(商品表Commodity信息)")
    public QueryResponseResult findAllBycomBus(Integer comBus){
        return commodityService.findAllBycomBus(comBus);

    }



    /**
     * 通过前台传递的商品Id 查询该商品详细信息
     * @param comId
     * @return
     */
    @RequestMapping(value = "/loadByIdCommodity",method = RequestMethod.GET)
    @ApiOperation(value = "查询商品详细信息",notes = "所需参数:商品id(comBus)" +
            "\n"+"返回参数：商品基本信息(商品表Commodity信息)")
    public QueryResponseResult loadByIdCommodity(int comId){
        return commodityService.loadById(comId);
    }


    /**
     * 通过前台传递的商家Id 查询该商家拥有的所有商品分类
     * @param busId
     * @return
     */
    @RequestMapping(value = "/loadByComIdCommodityType",method = RequestMethod.GET)
    @ApiOperation(value = "查询该商家所有的商品分类",notes = "所需参数:商家id(busId)" +
            "\n"+"返回参数：商品基本信息(商品分类表CommodityType信息)")
    public QueryResponseResult loadByComIdCommodityType(int busId){
        return commodityService.findAllBybusId(busId);
    }

    /**
     * 通过前台传递的商品分类ID 查询该分类下的商品
     * @param comType
     * @return
     */
    @RequestMapping(value = "/loadByComTypecommodity",method = RequestMethod.GET)
    @ApiOperation(value = " 通过前台传递的商品分类ID 查询该分类下的商品",notes = "所需参数:商品分类id(comType)" +
            "\n"+"返回参数：商品基本信息(商品分类表CommodityType信息)")
    public QueryResponseResult loadByComTypecommodity(int comType){
        return commodityService.findAllBycomType(comType);
    }
}
