package com.hellojava.controller;


import com.hellojava.entity.Evaluation;
import com.hellojava.service.EvaluationService;
import com.hellojava.utils.UploadPic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "评价提交")
@Controller
@RequestMapping("/evaluate")
@ResponseBody
public class EvaluateController {
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private UploadPic pic;


    @RequestMapping(value = "/saveEvaluate", method = RequestMethod.POST)
    @ApiOperation(value = "通过前台数据提交评价",notes = "所需参数：评价表evaluation基本信息")
    public void SaveEvaluate (Evaluation evaluation){
        evaluationService.saveEvaluation(evaluation);
    }
}
