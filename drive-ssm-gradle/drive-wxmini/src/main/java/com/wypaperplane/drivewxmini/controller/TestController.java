package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/wxmini")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(path="/apiTestA", method = RequestMethod.GET)
    public ResponseResult apiTestA() {
        logger.info("key--- {}", "value----");
        return ResponseResult.success(ResponseCode.SUCCESS, "需要认证");
    }

    @RequestMapping(path="/apiTestB", method = RequestMethod.GET)
    public ResponseResult apiTestB(){
        return ResponseResult.success(ResponseCode.SUCCESS, "无需认证");
    }
}
