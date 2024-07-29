package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.WxAppointmentService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.drivewxmini.entity.AppointmentClassDuring;
import com.wypaperplane.drivewxmini.mapper.AppointmentClassDuringMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/wxmini/teacher")
public class AppointmentClassDuringController {

    @Autowired
    private WxAppointmentService wxAppointmentService;

    @Autowired
    private AppointmentClassDuringMapper appointmentClassDuringMapper;

    @RequestMapping(path="/appointmentClassDuringInfo", method= RequestMethod.GET)
    public ResponseResult appointmentClassDuringInfo(@RequestParam(value = "teacherId") Integer teacherId) {
        Map<String, Object> mMap = new LinkedHashMap<>();
        mMap.put("appointmentClassList", wxAppointmentService.getAppointmentClass(teacherId));
        mMap.put("appointmentDuringList", wxAppointmentService.getAppointmentDuring(teacherId));
        mMap.put("appointmentClassDuringList", wxAppointmentService.getAppointmentClassDuring(teacherId));
        return ResponseResult.success(ResponseCode.SUCCESS, mMap);
    }

    @RequestMapping(path="/appointmentClassDuringSave", method= RequestMethod.POST)
    public ResponseResult appointmentClassDuringSave(@RequestBody @Validated AppointmentClassDuring appointmentClassDuring) {
        appointmentClassDuringMapper.save(appointmentClassDuring);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }
}
