package com.hellojava.controller;


import com.hellojava.response.QueryResponseResult;
import com.hellojava.service.CarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "轮播图")
@Controller
@RequestMapping("/carousel")
@ResponseBody
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @RequestMapping(value = "/queryCarousel", method = RequestMethod.POST)
    @ApiOperation(value = "轮播图")
    public QueryResponseResult QueryCarousel(){
        return carouselService.queryCarousel();
    }
}
