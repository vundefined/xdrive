package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.QrInviteService;
import com.wypaperplane.drivewxmini.service.WxMiniService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.entity.QrInvite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wxmini/teacher")
public class QrInviteController {

    private final Logger logger = LoggerFactory.getLogger(QrInviteController.class);

    @Autowired
    private QrInviteService qrInviteService;

    @Autowired
    private WxMiniService wxMiniService;

    @RequestMapping(path="/qrInvite", method= RequestMethod.GET)
    public ResponseResult getQrInvite(@RequestParam(value = "teacherId") Integer teacherId) {
        QrInvite qrInvite = qrInviteService.mSelect(teacherId);
        return ResponseResult.success(ResponseCode.SUCCESS, qrInvite);
    }

    @RequestMapping(path="/qrInviteSave", method= RequestMethod.POST)
    public ResponseResult qrInviteSave(@RequestBody @Validated QrInvite qrInvite) {
        String qrImg = qrInviteService.mInsert(qrInvite);
        // 用于控制加入数量, 此处为设为默认0
        wxMiniService.putQrInviteCount(qrInvite.getTeacherId(), (byte) 0);
        return ResponseResult.success(ResponseCode.SUCCESS, qrImg);
    }
}
