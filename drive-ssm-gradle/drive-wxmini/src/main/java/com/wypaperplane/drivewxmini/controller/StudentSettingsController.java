package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.StudentSettingsService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.entity.StudentSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/wxmini/student")
public class StudentSettingsController {

    @Autowired
    private StudentSettingsService studentSettingsService;

    @RequestMapping(path="/settings", method= RequestMethod.GET)
    public ResponseResult settings(
            @RequestParam(value="stype") Byte stype,
            @RequestParam(value = "studentId") Integer studentId) {
        StudentSettings studentSettings = studentSettingsService.mSelect(studentId, stype);
        if (ObjectUtils.isEmpty(studentSettings)) {
            studentSettings = new StudentSettings();
        }
        return ResponseResult.success(ResponseCode.SUCCESS, studentSettings);
    }

    @RequestMapping(path="/settingsSave", method = RequestMethod.POST)
    public ResponseResult settingsSave(@RequestBody StudentSettings studentSettings) {
        studentSettingsService.mInsert(studentSettings);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }
}
