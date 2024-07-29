package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.drivewxmini.entity.Feedback;
import com.wypaperplane.drivewxmini.mapper.FeedbackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@RequestMapping("/wxmini/feedback")
public class FeedbackController {

    private final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackMapper feedbackMapper;

    @RequestMapping(path="/history", method= RequestMethod.GET)
    public ResponseResult history(@RequestParam(value = "wxUserId") Integer wxUserId) {
        Example example = new Example(Feedback.class, true, true);
        example.createCriteria().andEqualTo("wxUserId", wxUserId);
        List<Feedback> feedbackList = feedbackMapper.selectByExample(example);
        return ResponseResult.success(ResponseCode.SUCCESS, feedbackList);
    }

    @RequestMapping(path="/commit", method= RequestMethod.POST)
    public ResponseResult commit(@RequestBody @Validated Feedback feedback) {
        feedbackMapper.insertSelective(feedback);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }
}
