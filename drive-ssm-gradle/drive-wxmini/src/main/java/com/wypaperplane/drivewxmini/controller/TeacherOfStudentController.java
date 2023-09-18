package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.*;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.entity.*;
import com.wypaperplane.syscore.mapper.TeacherOfStudentMapper;
import com.wypaperplane.syscore.vo.TeacherDetailVo;
import com.wypaperplane.syscore.vo.TeacherOfStudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(path="/wxmini/student")
public class TeacherOfStudentController {

    private final Logger logger = LoggerFactory.getLogger(TeacherOfStudentController.class);

    @Autowired
    private TeacherOfStudentMapper teacherOfStudentMapper;

    @Autowired
    private QrInviteService qrInviteService;

    @Autowired
    private WxMiniService wxMiniService;

    @Autowired
    private TeacherOfStudentService teacherOfStudentService;

    @Autowired
    private StudentSettingsService studentSettingsService;

    /*
     * 学员端 与教练绑定
     * */
    // TODO 每位学员最多只能绑定3条数据
    @RequestMapping(path="/bindTeacher", method = RequestMethod.POST)
    public ResponseResult bindTeacher(@RequestBody @Validated TeacherOfStudent teacherOfStudent) {
        if (teacherOfStudent.getQrType() == 0) {
            QrInvite qrInvite = qrInviteService.mSelect(teacherOfStudent.getTeacherId());
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime expiredTime = qrInvite.getUpdateTime().plusMinutes(qrInvite.getQrTime());
            if (expiredTime.isBefore(currentTime)) {
                return ResponseResult.failed(ResponseCode.QREXPIRED);
            }
            Byte mCount = wxMiniService.getQrInviteCount(teacherOfStudent.getTeacherId());
            wxMiniService.putQrInviteCount(teacherOfStudent.getTeacherId(), (byte) (mCount + 1));
            if (mCount >= qrInvite.getQrCount()) {
                return ResponseResult.failed(ResponseCode.QRCOUNT);
            }
        }

        TeacherOfStudent tos = teacherOfStudentService.mSelect(teacherOfStudent.getTeacherId(), teacherOfStudent.getStudentId());
        if (ObjectUtils.isEmpty(tos)) {
            teacherOfStudentMapper.insertSelective(teacherOfStudent);
            return ResponseResult.success(ResponseCode.SUCCESS);
        } else {
            return ResponseResult.failed(ResponseCode.DUPLICATE_TEACHER);
        }
    }

    @RequestMapping(path="/unBindTeacher", method = RequestMethod.POST)
    public ResponseResult unBindTeacher(@RequestBody @Validated TeacherOfStudent teacherOfStudent) {
        studentSettingsService.mDelete(teacherOfStudent.getStudentId(), (byte) 0);
        teacherOfStudentService.mDelete(teacherOfStudent);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    /*
     * 教练端 查看我绑定的学员 基本信息
     * */
    @RequestMapping(path="/studentBaseList", method = RequestMethod.GET)
    public ResponseResult studentBaseList(@RequestParam(value = "teacherId") Integer teacherId) {
        List<TeacherOfStudentVo> studentBaseVoList = teacherOfStudentMapper.studentBase(teacherId);
        return ResponseResult.success(ResponseCode.SUCCESS, studentBaseVoList);
    }

    /*
     * 学员端 查看我绑定的教练 基本信息
     * */
    @RequestMapping(path="/teacherBaseList", method = RequestMethod.GET)
    public ResponseResult teacherBaseList(@RequestParam(value = "studentId") Integer studentId) {
        List<TeacherOfStudentVo> teacherBaseVoList = teacherOfStudentMapper.teacherBase(studentId);
        return ResponseResult.success(ResponseCode.SUCCESS, teacherBaseVoList);
    }

    /*
     * 学员端 查看我绑定的教练 详细信息
     * */
    @RequestMapping(path="/teacherDetail", method = RequestMethod.GET)
    public ResponseResult teacherDetail(@RequestParam(value = "teacherId") Integer teacherId) {
        TeacherDetailVo teacherDetailVo = teacherOfStudentMapper.teacherDetail(teacherId);
        return ResponseResult.success(ResponseCode.SUCCESS, teacherDetailVo);
    }
}
