package com.hellojava.controller;

import com.hellojava.response.QueryResponseResult;
import com.hellojava.service.BusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Api(tags = "商家操作")
@Controller
@RequestMapping("/business")
@ResponseBody
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 通过前台传递的商品类型 查询同一类型所有商家
     * @param typePid
     * @return
     */
    @RequestMapping(value = "/loadByTypeId/{typePid}",method = RequestMethod.GET)
    @ApiOperation(value = "查询同一类型的所有商家")
    public QueryResponseResult loadByTypeId(int typePid){
        return businessService.loadByTypeId(typePid);

    }


    /**
     * 通过前台传递的商品类型 查询同一类型所有商家
     * @param busId
     * @return
     */
    @RequestMapping(value = "/loadBybusId/{busId}",method = RequestMethod.GET)
    @ApiOperation(value = "通过id查询商家详细信息")
    public QueryResponseResult loadBybusId(int busId){
        return businessService.loadBybusId(busId);

    }



    /**
     * 通过前台传递的商家id 查询商家的所有评价
     * @param busId
     * @return
     */
    @RequestMapping(value = "/selectByBusIdEvaluation/{busId}",method = RequestMethod.GET)
    @ApiOperation(value = "通过前台传递的商家id 查询商家的所有评价")
    public QueryResponseResult selectByBusIdEvaluation(int busId){
        return businessService.selectByBusIdEvaluation(busId);

    }

}
