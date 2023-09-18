package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.RabbitmqService;
import com.wypaperplane.drivewxmini.service.StudentSettingsService;
import com.wypaperplane.drivewxmini.service.WxAppointmentService;
import com.wypaperplane.drivewxmini.service.WxAuthService;
import com.wypaperplane.drivewxmini.vo.AppointmentStudentVo;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.*;

@RestController
@RequestMapping(path="/wxmini/student")
public class AppointmentStudentController {

    @Autowired
    private WxAuthService wxAuthService;

    @Autowired
    private WxAppointmentService wxAppointmentService;

    @Autowired
    private StudentSettingsService studentSettingsService;

    @Autowired
    private RabbitmqService rabbitmqService;

    /*
     * 学员端 根据学员设置的 默认教练 和 默认驾驶证类型
     * 查找该教练的预约规则等信息
     * */
    @RequestMapping(path="/appointmentRule", method = RequestMethod.GET)
    public ResponseResult appointmentRule(@RequestParam(value = "studentId") Integer studentId) {
        StudentSettings defaultTeacher = studentSettingsService.mSelect(studentId, (byte) 0);
        if (ObjectUtils.isEmpty(defaultTeacher)) {
            return ResponseResult.failed(ResponseCode.NO_TEACHER);
        }

        StudentSettings defaultClass = studentSettingsService.mSelect(studentId, (byte) 1);
        if (ObjectUtils.isEmpty(defaultClass)) {
            return ResponseResult.failed(ResponseCode.NO_CLASS);
        }

        // 判断教练是否设置了预约规则
        AppointmentRule appointmentRule = wxAppointmentService.getAppointmentRule(defaultTeacher.getCommonId());
        if (ObjectUtils.isEmpty(appointmentRule.getId())) {
            return ResponseResult.failed(ResponseCode.NO_TEACHER_RULE);
        }
        // 默认驾驶证类型与教练不一致
        AppointmentClassDuring appointmentClassDuring = wxAppointmentService.getAppointmentClassDuring(defaultTeacher.getCommonId(), defaultClass.getCommonId());
        if (ObjectUtils.isEmpty(appointmentClassDuring)) {
            return ResponseResult.failed(ResponseCode.NO_CLASS_MATCH);
        }

        Map<String, Object> appointmentRuleMap = new LinkedHashMap<>();
        appointmentRuleMap.put("classId", appointmentClassDuring.getClassId());
        appointmentRuleMap.put("classIndex", appointmentClassDuring.getClassIndex());
        appointmentRuleMap.put("wxUserBase", wxAuthService.mSelect(defaultTeacher.getCommonId()));
        appointmentRuleMap.put("appointmentRule", appointmentRule);
        appointmentRuleMap.put("appointmentDuringList", wxAppointmentService.getAppointmentDuring(appointmentClassDuring.getDuringIds()));
        return ResponseResult.success(ResponseCode.SUCCESS, appointmentRuleMap);
    }

    @RequestMapping(path="/appointmentCommit", method = RequestMethod.POST)
    public ResponseResult appointmentCommit(@RequestBody @Validated AppointmentStudentVo appointmentStudentVo) {
        Integer[] ruleRest = appointmentStudentVo.getAppointmentRuleRest();
        Integer classId = appointmentStudentVo.getClassId();
        LocalTime ruleStartTime = appointmentStudentVo.getAppointmentRuleStartTime();
        LocalTime ruleEndTime = appointmentStudentVo.getAppointmentRuleEndTime();

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();

        // 判断是否教练的休息日
        int dayOfWeek = localDate.get(ChronoField.DAY_OF_WEEK);
        Boolean isWeek = Arrays.stream(ruleRest).anyMatch(item -> item == dayOfWeek);
        if (isWeek) {
            return ResponseResult.failed(ResponseCode.NO_WEEK_MATCH);
        }

        // 限制一位学员，同一种驾驶类型，一天只能预约一次
        Boolean hasToday = wxAppointmentService.hasAppointmentToday(appointmentStudentVo.getStudentId(), classId, localDate);
        if (hasToday) {
            return ResponseResult.failed(ResponseCode.DUPLICATE_CLASS);
        }

        if (localTime.isBefore(ruleEndTime) && localTime.isAfter(ruleStartTime)) {
            rabbitmqService.sendMessageAppointmentDrive(appointmentStudentVo);
            return ResponseResult.success(ResponseCode.SUCCESS);
        } else {
            return ResponseResult.failed(ResponseCode.NO_TIME_MATCH);
        }
    }
}
