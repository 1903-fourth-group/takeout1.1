package com.hellojava.controller;

import com.hellojava.utils.UploadPic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 使用用例
 * 七牛云接口为内部调用接口
 * 调用uploadPic的getPic方法：将图片文件放入七牛云，并返回相应图片名，直接存入该去的库
 */
@Controller
@Api(tags ="图片操作")
public class QiniuExample {
    @Autowired
    private UploadPic uploadPic;

    @RequestMapping(value = "/exampleUpload",method = RequestMethod.POST)
    @ApiOperation(value = "图片传千牛云测试")
    @ApiImplicitParam(paramType = "query", name = "picc", value = "图片文件", required = true, dataType = "MultipartFile")
    public String exampleUpload(@RequestParam("picc") MultipartFile picc){
        String pic = uploadPic.getPic (picc);
        System.out.println (pic);
        return "test.html";
    }
}