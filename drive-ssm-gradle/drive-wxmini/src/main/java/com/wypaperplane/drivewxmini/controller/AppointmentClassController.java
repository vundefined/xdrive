package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.AppointmentClassService;
import com.wypaperplane.drivewxmini.service.WxAppointmentService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.entity.AppointmentClass;
import com.wypaperplane.syscore.mapper.AppointmentClassMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wxmini/teacher")
public class AppointmentClassController {

    private final Logger logger = LoggerFactory.getLogger(AppointmentClassController.class);

    @Autowired
    private WxAppointmentService wxAppointmentService;

    @Autowired
    private AppointmentClassMapper appointmentClassMapper;

    @Autowired
    private AppointmentClassService appointmentClassService;

    @RequestMapping(path="/appointmentClassInfo", method= RequestMethod.GET)
    public ResponseResult appointmentClassInfo(@RequestParam(value = "teacherId") Integer teacherId) {
        return ResponseResult.success(ResponseCode.SUCCESS, wxAppointmentService.getAppointmentClass(teacherId));
    }

    @RequestMapping(path="/appointmentClassDelete/{ids}", method= RequestMethod.DELETE)
    public ResponseResult appointmentClassDelete(@PathVariable String ids) {
        appointmentClassService.mDelete(ids);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    // TODO 只能插入四条数据
    @RequestMapping(path="/appointmentClassSave", method= RequestMethod.POST)
    public ResponseResult appointmentClassSave(@RequestBody @Validated AppointmentClass appointmentClass) {
        Boolean hasOwn = wxAppointmentService.getAppointmentClass(appointmentClass.getWxUserId(), appointmentClass.getClassId());
        if (hasOwn) {
            appointmentClassMapper.insertSelective(appointmentClass);
            return ResponseResult.success(ResponseCode.SUCCESS);
        } else {
            return ResponseResult.failed(ResponseCode.DUPLICATE_KEY);
        }
    }
}
