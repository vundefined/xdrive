package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.entity.DriveLicenseClass;
import com.wypaperplane.drivewxmini.entity.OrderStudent;
import com.wypaperplane.drivewxmini.entity.QrRecommend;
import com.wypaperplane.drivewxmini.entity.TeacherOfStudent;
import com.wypaperplane.drivewxmini.service.*;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.drivewxmini.mapper.DriveLicenseClassMapper;
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
    private DriveLicenseClassMapper driveLicenseClassMapper;

    @Autowired
    private QrRecommendService qrRecommendService;

    @Autowired
    private TeacherOfStudentService teacherOfStudentService;

    @Autowired
    private OrderStudentService orderStudentService;

    /**
     * 判断费用是否交全
     * */
    @RequestMapping(path="/isCompletePayment", method = RequestMethod.POST)
    public ResponseResult isCompletePayment(@RequestBody Map<String, Object> body) {
        Integer teacherId = (Integer) body.get("teacherId");
        Integer studentId = (Integer) body.get("studentId");
        Integer classId = (Integer) body.get("classId");
        if (ObjectUtils.isEmpty(studentId)) {
            return ResponseResult.failed(ResponseCode.FAILED);
        }
        if (ObjectUtils.isEmpty(teacherId)) {
            return ResponseResult.failed(ResponseCode.FAILED);
        }
        if (ObjectUtils.isEmpty(classId)) {
            return ResponseResult.failed(ResponseCode.FAILED);
        }
        // 当学员是通过推广码申请预约时
        DriveLicenseClass driveLicenseClass = driveLicenseClassMapper.selectByPrimaryKey(classId);
        QrRecommend qrRecommend = qrRecommendService.mSelect(teacherId, driveLicenseClass.getpId());
        TeacherOfStudent teacherOfStudent = teacherOfStudentService.mSelect(teacherId, studentId);

        // 判断教练是否 根据驾驶证类型 设置了 驾驶培训报名费
        if (teacherOfStudent.getQrType() == 1 && ObjectUtils.isEmpty(qrRecommend)) {
            return ResponseResult.failed(ResponseCode.NO_APPLY_PRICE_A);
        } else if (teacherOfStudent.getQrType() == 2 && ObjectUtils.isEmpty(qrRecommend)) {
            return ResponseResult.failed(ResponseCode.NO_APPLY_PRICE_A);
        }

        // 判断学员是否交完驾驶培训报名费
        OrderStudent orderStudent = orderStudentService.mSelect(teacherId, studentId, driveLicenseClass.getpId());
        if (teacherOfStudent.getQrType() == 1 || teacherOfStudent.getQrType() == 2) {
            if (ObjectUtils.isEmpty(orderStudent)) {
                return ResponseResult.failed(ResponseCode.NO_APPLY_PRICE_B);
            } else if (orderStudent.getPayerTotal() < orderStudent.getTotal()) {
                return ResponseResult.failed(ResponseCode.NO_APPLY_PRICE_C);
            }
        }

        return ResponseResult.success(ResponseCode.SUCCESS);
    }
}
