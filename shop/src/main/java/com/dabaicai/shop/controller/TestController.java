package com.dabaicai.shop.controller;

import com.dabaicai.common.export.Export;
import com.dabaicai.common.export.ExportRqeuest;
import com.dabaicai.common.response.BasicResponse;
import com.dabaicai.shop.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "TestController", description = "测试")
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private Export export;

    @ApiOperation("测试")
    @PostMapping("/test")
    public Object test(){
        return testService.test();
    }

    @PostMapping("/test1")
    public void test1(HttpServletResponse response){
        ExportRqeuest exportRqeuest = new ExportRqeuest();
        exportRqeuest.setSheetName("测试1");
        exportRqeuest.setFileName("测试");
        BasicResponse basicResponse = testService.test();
        exportRqeuest.setResponseData(basicResponse.getData());
        try {
            export.export(exportRqeuest, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
