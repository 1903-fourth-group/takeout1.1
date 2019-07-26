package com.hellojava.controller;

import com.hellojava.response.QueryResponseResult;
import com.hellojava.service.BusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Api(tags = "商家操作")
@Controller
@RequestMapping("/business")
@ResponseBody
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 通过前台传递的商品类型 查询该类型下的所有分类表
     *
     * @param busTypeName
     * @return
     */
    @RequestMapping(value = "/loadByTypeId", method = RequestMethod.GET)
    @ApiOperation(value = "通过前台传递的商品类型 查询该类型下的所有分类表",notes = "所需参数：五大分类的名称"+"\n"
            +"返回参数：对应该分类下的子分类")
    public QueryResponseResult loadByTypeId(String busTypeName) {
        return businessService.selectBusinessTypeAll(busTypeName);

    }

    /**
     * 通过前台传递的商品类型 查询该类型下的所有商家
     *
     * @param busTypeName
     * @return
     */
    @RequestMapping(value = "/loadByTypeBusinessAll", method = RequestMethod.GET)
    @ApiOperation(value = "通过前台传递的商品类型 查询该类型下的所有商家",notes = "所需参数：五大分类的名称"+"\n"
            +"返回参数：对应该分类下的所有商家信息")
    public QueryResponseResult loadByTypeBusinessAll(String busTypeName) {
        return businessService.selectBusinessAll(busTypeName);
    }

    /**
     * 通过前台传递的商家分类编号 查询该类型下的所有商家
     *
     * @param busType
     * @return
     */
    @RequestMapping(value = "/loadByTypeIdAll", method = RequestMethod.GET)
    @ApiOperation(value = "通过前台传递的商家分类编号 查询该类型下的所有商家",notes = "所需参数：小分类的id（busType）"+"\n"
            +"返回参数：对应该分类下的所有商家信息")
    public QueryResponseResult loadByTypeIdAll(int busType) {
        return businessService.selectBusinessAll(busType);
    }


    /**
     * 通过id查询商家详细信息
     *
     * @param busId
     * @return
     */
    @RequestMapping(value = "/loadBybusId", method = RequestMethod.GET)
    @ApiOperation(value = "通过id查询商家详细信息",notes = "商家id(busId)" + "\n"
            + "返回参数：商品基本信息（商家表business内所有字段信息）")
    public QueryResponseResult loadBybusId(int busId) {
        return businessService.loadBybusId(busId);

    }


    /**
     * 通过前台传递的商家id 查询商家的所有评价
     *
     * @param busId
     * @return
     */
    @RequestMapping(value = "/selectByBusIdEvaluation", method = RequestMethod.GET)
    @ApiOperation(value = "通过前台传递的商家id 查询商家的所有评价",notes = "商家id(busId)" + "\n"
            + "返回参数：评价信息（评价表evaluation内所有字段信息）")
    public QueryResponseResult selectByBusIdEvaluation(int busId) {
        return businessService.selectByBusIdEvaluation(busId);

    }

    /**
     * 通过前台传递的用户id 查询用户收藏的所有店铺
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/selectByUserIdCollection", method = RequestMethod.GET)
    @ApiOperation(value = "通过前台传递的用户id 查询用户收藏的所有店铺",notes = "用户id(userId)" + "\n"
            + "返回参数：商品基本信息（商家表business内所有字段信息）")
    public QueryResponseResult selectByUserIdCollection(int userId) {
        return businessService.selectByUserIdCollection(userId);
    }

    /**
     * 通过前台提供的userId和店铺id 实现用户收藏此店铺
     *
     * @param userId
     * @param busId
     * @return 1为成功  0为失败
     */
    @PostMapping("/userAddCollection")
    @ApiOperation(value = "通过前台提供的userId和店铺id 实现用户收藏此店铺  ", notes = "用户id(userId)、商家id(busId)" + "\n"
            + "返回参数：1代表收藏成功 2代表收藏失败")
    public int userAddCollection(@RequestParam("userId") Integer userId,
                                 @RequestParam("busId") Integer busId) {
        return businessService.addUserIdCollection(userId, busId);
    }


    /**
     * 通过前台提供的userId和店铺id 实现用户删除此店铺收藏
     *
     * @param userId
     * @param busId
     * @return 1为成功  0为失败
     */
    @PostMapping("/delectAddCollection")
    @ApiOperation(value = "通过前台提供的userId和店铺id 实现用户删除此店铺收藏 ", notes = "用户id(userId)、商家id(busId)" + "\n"
            + "返回参数：1代表取消收藏成功 2代表取消收藏失败")
    public int delectAddCollection(@RequestParam("userId") Integer userId,
                                   @RequestParam("busId") Integer busId) {
        return businessService.delectUserIdCollection(userId, busId);
    }
}
