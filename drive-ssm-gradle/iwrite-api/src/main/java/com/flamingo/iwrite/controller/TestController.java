package com.flamingo.iwrite.controller;

import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/iwrite")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(path="/testa", method = RequestMethod.GET)
    public ResponseResult testa() {

        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="/testb", method = RequestMethod.POST)
    public ResponseResult testb() {

        return ResponseResult.success(ResponseCode.SUCCESS);
    }
}
