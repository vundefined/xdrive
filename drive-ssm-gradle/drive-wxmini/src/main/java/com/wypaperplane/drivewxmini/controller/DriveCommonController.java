package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.NotificationService;
import com.wypaperplane.drivewxmini.service.WxAppointmentService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wxmini/common")
public class DriveCommonController {

    private final Logger logger = LoggerFactory.getLogger(DriveCommonController.class);

    @Autowired
    private WxAppointmentService wxAppointmentService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(path="/driveLicenseClassList", method= RequestMethod.GET)
    public ResponseResult driveLicenseClassList() {
        return ResponseResult.success(ResponseCode.SUCCESS, wxAppointmentService.getDriveLicenseClassList());
    }

    @RequestMapping(path="/driveLicenseClassTree", method= RequestMethod.GET)
    public ResponseResult driveLicenseClassTree() {
        return ResponseResult.success(ResponseCode.SUCCESS, wxAppointmentService.getDriveLicenseClassTree());
    }

    @RequestMapping(path="/notification", method= RequestMethod.GET)
    public ResponseResult notification(@RequestParam(value="nType") Byte nType) {
        return ResponseResult.success(ResponseCode.SUCCESS, notificationService.mSelect(nType));
    }
}
