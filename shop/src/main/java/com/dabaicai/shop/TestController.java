package com.dabaicai.shop;

import com.dabaicai.common.export.Export;
import com.dabaicai.common.export.ExportRqeuest;
import com.dabaicai.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private Export export;

    @RequestMapping("/test")
    public Object test(){
        return testService.test();
    }

    @RequestMapping("/test1")
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
