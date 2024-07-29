package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.StudentJobService;
import com.wypaperplane.drivewxmini.service.StudentSettingsService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.drivewxmini.entity.StudentJob;
import com.wypaperplane.drivewxmini.mapper.StudentJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wxmini/student")
public class StudentJobController {

    private final Logger logger = LoggerFactory.getLogger(StudentJobController.class);

    @Autowired
    private StudentJobMapper studentJobMapper;

    @Autowired
    private StudentJobService studentJobService;

    @Autowired
    private StudentSettingsService studentSettingsService;

    @RequestMapping(path="/jobInfo", method= RequestMethod.GET)
    public ResponseResult jobInfo(@RequestParam(value = "studentId") Integer studentId) {
        return ResponseResult.success(ResponseCode.SUCCESS, studentJobService.mSelect(studentId));
    }

    // TODO 根据驾驶证类型课程数量限制新增条目。如 C1 科一 到 科四，只能插入四条数据
    @RequestMapping(path="/jobInfoSave", method=RequestMethod.POST)
    public ResponseResult jobInfoSave(@RequestBody @Validated StudentJob studentJob) {
        Boolean hasOwn = studentJobService.hasOwn(studentJob.getStudentId(), studentJob.getClassId());
        if (hasOwn) {
            studentJob.setStudentId(studentJob.getStudentId());
            studentJobMapper.insertSelective(studentJob);
            return ResponseResult.success(ResponseCode.SUCCESS);
        } else {
            return ResponseResult.failed(ResponseCode.DUPLICATE_KEY);
        }
    }

    @RequestMapping(path="jobInfoDelete", method=RequestMethod.POST)
    public ResponseResult jobInfoDelete(@RequestBody StudentJob studentJob) {
        studentSettingsService.mDelete(studentJob.getStudentId(), (byte) 1);
        studentJobService.mDelete(studentJob);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }
}
