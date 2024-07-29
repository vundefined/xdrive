package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.WxAuthService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.properties.WxStudentProperties;
import com.wypaperplane.syscore.properties.WxTeacherProperties;
import com.wypaperplane.drivewxmini.entity.WxUserBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/wxmini/auth")
public class WxAuthController {

    private final Logger logger = LoggerFactory.getLogger(WxAuthController.class);

    @Autowired
    private WxAuthService wxAuthService;

    @Autowired
    private WxTeacherProperties wxTeacherProperties;

    @Autowired
    private WxStudentProperties wxStudentProperties;

    @RequestMapping(path="/wxTeacherLogin", method= RequestMethod.GET)
    private ResponseResult wxTeacherLogin(@RequestParam String code) {
        Map<String, Object> userInfoMap = wxAuthService.handleLogin(
                code,
                (byte) 0,
                wxTeacherProperties.getAppid(),
                wxTeacherProperties.getSecret());
        return ResponseResult.success(ResponseCode.SUCCESS, userInfoMap);
    }

    @RequestMapping(path="/wxStudentLogin", method= RequestMethod.GET)
    private ResponseResult wxStudentLogin(@RequestParam String code) {
        Map<String, Object> userInfoMap = wxAuthService.handleLogin(
                code,
                (byte) 1,
                wxStudentProperties.getAppid(),
                wxStudentProperties.getSecret());
        return ResponseResult.success(ResponseCode.SUCCESS, userInfoMap);
    }

    @RequestMapping(path="/baseInfo", method=RequestMethod.GET)
    public ResponseResult baseInfo(@RequestParam(value = "wxUserId") Integer wxUserId){
        return ResponseResult.success(ResponseCode.SUCCESS, wxAuthService.mSelect(wxUserId));
    }

    @RequestMapping(path="/baseInfoUpdate", method=RequestMethod.POST)
    public ResponseResult baseInfoUpdate(@RequestBody @Validated WxUserBase wxUserBase){
        wxAuthService.mUpdate(wxUserBase);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }
}
