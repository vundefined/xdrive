package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.WxAppointmentService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.entity.AppointmentRule;
import com.wypaperplane.syscore.mapper.AppointmentRuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wxmini/teacher")
public class AppointmentRuleController {

    private final Logger logger = LoggerFactory.getLogger(AppointmentRuleController.class);

    @Autowired
    private WxAppointmentService wxAppointmentService;

    @Autowired
    private AppointmentRuleMapper appointmentRuleMapper;

    @RequestMapping(path="/appointmentRuleInfo", method= RequestMethod.GET)
    public ResponseResult appointmentRuleInfo(@RequestParam(value = "teacherId") Integer teacherId) {
        AppointmentRule appointmentRule = wxAppointmentService.getAppointmentRule(teacherId);
        return ResponseResult.success(ResponseCode.SUCCESS, appointmentRule);
    }

    @RequestMapping(path="/appointmentRuleSave", method=RequestMethod.POST)
    public ResponseResult appointmentRuleSave(@RequestBody @Validated AppointmentRule appointmentRule) {
        appointmentRuleMapper.save(appointmentRule);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }
}
