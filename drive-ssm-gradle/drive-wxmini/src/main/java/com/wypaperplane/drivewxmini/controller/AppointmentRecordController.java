package com.wypaperplane.drivewxmini.controller;

import com.github.pagehelper.PageHelper;
import com.wypaperplane.drivewxmini.service.StudentSettingsService;
import com.wypaperplane.drivewxmini.service.WxAppointmentService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.entity.AppointmentClassDuring;
import com.wypaperplane.syscore.entity.AppointmentRecord;
import com.wypaperplane.syscore.entity.StudentSettings;
import com.wypaperplane.syscore.mapper.AppointmentRecordMapper;
import com.wypaperplane.syscore.vo.AppointmentRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 教师 和 学员 共有的处理器
@RestController
@RequestMapping(path="/wxmini/appointmentRecord")
public class AppointmentRecordController {

    private final Logger logger = LoggerFactory.getLogger(AppointmentRecordController.class);

    @Autowired
    private AppointmentRecordMapper appointmentRecordMapper;

    @Autowired
    private WxAppointmentService wxAppointmentService;

    @Autowired
    private StudentSettingsService studentSettingsService;

    // 教师 按当日条件 查看所有学员预约概览
    @RequestMapping(path="/ttodayPreview", method = RequestMethod.GET)
    public ResponseResult todayPreview(@RequestParam(value = "teacherId") Integer teacherId) {
        return ResponseResult.success(ResponseCode.SUCCESS, wxAppointmentService.todayPreviewService(teacherId));
    }

    // 学员 按当日条件 查看所有学员预约概览
    @RequestMapping(path="/stodayPreview", method = RequestMethod.GET)
    public ResponseResult stodayPreview(@RequestParam(value = "studentId") Integer studentId) {
        StudentSettings studentSettings = studentSettingsService.mSelect(studentId, (byte) 0);
        if (ObjectUtils.isEmpty(studentSettings)) {
            return ResponseResult.failed(ResponseCode.NO_TEACHER);
        }
        return ResponseResult.success(ResponseCode.SUCCESS, wxAppointmentService.todayPreviewService(studentSettings.getCommonId()));
    }

    // 教师 按当日条件 获取学员提交的预约信息, 以便审批
    @RequestMapping(path="/teacherList", method = RequestMethod.GET)
    public ResponseResult teacherList(
            @RequestParam(value = "classId", required = false) Integer classId,
            @RequestParam(value = "teacherId") Integer teacherId) {
        LocalDate localDate = LocalDate.now();
        List<AppointmentClassDuring> appointmentClassDuringList = wxAppointmentService.getAppointmentClassDuring(teacherId);
        List<AppointmentRecordVo> appointmentRecordVoList = appointmentRecordMapper.selectByTeacher(teacherId, classId, localDate + "%");
        Map<String, Object> mMap = new LinkedHashMap<>();
        mMap.put("appointmentRecordVoList", appointmentRecordVoList);
        mMap.put("appointmentClassDuringList", appointmentClassDuringList);
        return ResponseResult.success(ResponseCode.SUCCESS, mMap);
    }

    // 学员 获取预约记录
    @RequestMapping(path="/studentList" ,method = RequestMethod.GET)
    public ResponseResult studentList(@RequestParam(value = "studentId") Integer studentId) {
        PageHelper.startPage(1, 10);
        List<AppointmentRecordVo> appointmentRecordVoList = appointmentRecordMapper.selectByStudent(studentId);
        return ResponseResult.success(ResponseCode.SUCCESS, appointmentRecordVoList);
    }

    @RequestMapping(path="/changeStatus", method = RequestMethod.POST)
    public ResponseResult changeStatus(@RequestBody AppointmentRecord appointmentRecord) {
        appointmentRecordMapper.updateByPrimaryKeySelective(appointmentRecord);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    // 教师 批量处理 未通过 的学员
    // TODO 加一个定时自动处理功能
    @RequestMapping(path="/tRejected", method = RequestMethod.GET)
    public ResponseResult appointmentRejected(@RequestParam(value = "teacherId") Integer teacherId) {
        LocalDate localDate = LocalDate.now();
        String addTimeLike = localDate.toString() + "%";
        appointmentRecordMapper.updateStatus(teacherId, 0, addTimeLike, 2);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }
}
