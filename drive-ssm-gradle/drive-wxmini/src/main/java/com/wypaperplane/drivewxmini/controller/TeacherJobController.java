package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.entity.TeacherJob;
import com.wypaperplane.syscore.mapper.TeacherJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDate;

@RestController
@RequestMapping("/wxmini/teacher")
public class TeacherJobController {

    private final Logger logger = LoggerFactory.getLogger(TeacherJobController.class);

    @Autowired
    private TeacherJobMapper teacherJobMapper;

    @RequestMapping(path="/jobInfo", method= RequestMethod.GET)
    public ResponseResult jobInfo(@RequestParam(value = "teacherId") Integer teacherId) {
        Example example = new Example(TeacherJob.class, true, true);
        example.createCriteria().andEqualTo("wxUserId", teacherId);
        TeacherJob teacherJob = teacherJobMapper.selectOneByExample(example);
        if (ObjectUtils.isEmpty(teacherJob)) {
            teacherJob = new TeacherJob();
            teacherJob.setTime(LocalDate.of(1980, 1, 1));
        }
        return ResponseResult.success(ResponseCode.SUCCESS, teacherJob);
    }

    @RequestMapping(path="/jobInfoSave", method=RequestMethod.POST)
    public ResponseResult jobInfoSave(@RequestBody @Validated TeacherJob teacherJob) {
        teacherJobMapper.save(teacherJob);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }
}
