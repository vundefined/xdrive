package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.QrRecommendService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.drivewxmini.entity.QrRecommend;
import com.wypaperplane.syscore.enumm.StorageCategory;
import com.wypaperplane.syscore.utils.QrcodeUtil;
import com.wypaperplane.drivewxmini.vo.QrRecommendVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 教练拉新
 * */
@RestController
@RequestMapping("/wxmini/teacher")
public class QrRecommendController {

    private final Logger logger = LoggerFactory.getLogger(QrRecommendController.class);

    @Autowired
    private QrRecommendService qrRecommendService;

    @RequestMapping(path="/qrRecommend", method= RequestMethod.GET)
    public ResponseResult qrRecommend(@RequestParam(value = "teacherId") Integer teacherId) {
        return ResponseResult.success(ResponseCode.SUCCESS, qrRecommendService.mSelect(teacherId));
    }

    // TODO 只能插入四条数据
    @RequestMapping(path="/qrRecommendSave", method= RequestMethod.POST)
    public ResponseResult qrRecommendSave(@RequestBody @Validated QrRecommend qrRecommend) {
        qrRecommendService.mInsert(qrRecommend);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    /*
     * 学员端 查看我绑定的教练 推广信息
     * */
    @RequestMapping(path="/getRecommendPrice", method = RequestMethod.GET)
    public ResponseResult getRecommendPrice(@RequestParam(value = "teacherId") Integer teacherId) {
        List<QrRecommendVo> qrRecommendList = qrRecommendService.mSelectRecommendAndClass(teacherId);
        return ResponseResult.success(ResponseCode.SUCCESS, qrRecommendList);
    }

    @RequestMapping(path="/generateQrcode", method= RequestMethod.GET)
    public ResponseResult generateQrcode(@RequestParam(value = "teacherId") Integer teacherId) {
        StringBuilder content = new StringBuilder();
        content.append("https://www.wypaperplane.com/wxstudent/?teacherId=");
        content.append(teacherId);
        content.append("&recommend=");
        content.append(teacherId);
        content.append("&qrType=1");

        String dirName = StorageCategory.QRCODE.getName();

        StringBuilder fileName = new StringBuilder();
        fileName.append("driverecommend").append(teacherId).append(".png");

        QrcodeUtil.generateQrcode(content.toString(), "", dirName, fileName.toString());

        StringBuilder qrImg = new StringBuilder();
        qrImg.append(dirName).append("/").append(fileName);

        return ResponseResult.success(ResponseCode.SUCCESS, qrImg.toString());
    }
}
