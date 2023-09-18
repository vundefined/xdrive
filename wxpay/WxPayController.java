package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.*;
import com.wypaperplane.drivewxmini.wxpayments.ProfitsharingUnfreezeResponse;
import com.wypaperplane.drivewxmini.wxpayments.UnsplitAmountResponse;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.entity.*;
import com.wypaperplane.syscore.mapper.DriveLicenseClassMapper;
import com.wypaperplane.syscore.mapper.WxUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="/wxmini/student")
public class WxPayController {

    private final Logger logger = LoggerFactory.getLogger(WxPayController.class);

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private DriveLicenseClassMapper driveLicenseClassMapper;

    @Autowired
    private QrRecommendService qrRecommendService;

    @Autowired
    private TeacherOfStudentService teacherOfStudentService;

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private OrderStudentService orderStudentService;

    @Autowired
    private RabbitmqService rabbitmqService;

    /**
     * 下单 支付
     * */
    @RequestMapping(path="/prepayTransactions", method = RequestMethod.POST)
    public ResponseResult prepayTransactions(@RequestBody Map<String, Object> body) {
        Integer studentId = (Integer) body.get("studentId");
        Integer price = (Integer) body.get("price");
        if (ObjectUtils.isEmpty(studentId)) {
            return ResponseResult.success(ResponseCode.UNAUTHENTICATION);
        }
        if (ObjectUtils.isEmpty(price)) {
            return ResponseResult.success(ResponseCode.NO_APPLY_PRICE_B);
        }
        if (price > 0) {
            WxUser wxUser = wxUserMapper.selectByPrimaryKey(studentId);
            Map<String, Object> mmap = wxPayService.transactions(wxUser.getOpenid(), price);
            return ResponseResult.success(ResponseCode.SUCCESS, mmap);
        } else {
            return ResponseResult.success(ResponseCode.NO_APPLY_PRICE_B);
        }
    }

    /**
     * 查询订单
     * */
    @RequestMapping(path="/queryOrderc", method = RequestMethod.POST)
    public ResponseResult queryOrderc(@RequestBody Map<String, Object> body) {
        Integer teacherId = (Integer) body.get("teacherId");
        Integer studentId = (Integer) body.get("studentId");
        Integer classId = (Integer) body.get("classId");
        String outTradeNo = (String) body.get("outTradeNo");
        if (ObjectUtils.isEmpty(studentId)) {
            return ResponseResult.failed(ResponseCode.FAILED);
        }
        if (ObjectUtils.isEmpty(teacherId)) {
            return ResponseResult.failed(ResponseCode.FAILED);
        }
        if (ObjectUtils.isEmpty(classId)) {
            return ResponseResult.failed(ResponseCode.FAILED);
        }
        if (ObjectUtils.isEmpty(outTradeNo)) {
            return ResponseResult.failed(ResponseCode.FAILED);
        }
        OrderStudent orderStudent = wxPayService.queryOrder(teacherId, studentId, classId, outTradeNo);
        if (!ObjectUtils.isEmpty(orderStudent)) {
            // 与教练分账 自动分账
            // rabbitmqService.sendMessageProfitsharingWxpay(orderStudent);
        }
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    /**
     * 与教练分账 手动分账
     * */
    @RequestMapping(path="/profitsharingc", method = RequestMethod.GET)
    public ResponseResult profitsharingc(@RequestParam(value = "primaryId") Integer primaryId) {
        OrderStudent orderStudent = orderStudentService.mSelect(primaryId);
        if(ObjectUtils.isEmpty(orderStudent)) {
            return ResponseResult.failed(ResponseCode.FAILED);
        }
        wxPayService.profitsharingToTeacher(orderStudent);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    /**
     * 解冻剩余资金
     * @param transactionId 微信订单号
     * @param outOrderNo 商户分账单号
     * */
    @RequestMapping(path="/profitsharingUnfreezec", method = RequestMethod.GET)
    public ResponseResult profitsharingUnfreezec(
            @RequestParam(value = "transactionId") String transactionId,
            @RequestParam(value = "outOrderNo") String outOrderNo) {
        ProfitsharingUnfreezeResponse profitsharingUnfreezeResponse = wxPayService.profitsharingUnfreeze(transactionId, outOrderNo);
        return ResponseResult.success(ResponseCode.SUCCESS, profitsharingUnfreezeResponse);
    }

    /**
     * 查询剩余待分金额
     * @param transactionId 微信订单号
     * */
    @RequestMapping(path="/profitsharingResiduec", method = RequestMethod.GET)
    public ResponseResult profitsharingResiduec(@RequestParam(value = "transactionId") String transactionId) {
        UnsplitAmountResponse unsplitAmountResponse = wxPayService.profitsharingResidue(transactionId);
        return ResponseResult.success(ResponseCode.SUCCESS, unsplitAmountResponse);
    }
}
