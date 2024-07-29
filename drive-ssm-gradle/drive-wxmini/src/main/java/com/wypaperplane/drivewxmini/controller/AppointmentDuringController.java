package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.drivewxmini.service.WxAppointmentService;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.drivewxmini.entity.AppointmentDuring;
import com.wypaperplane.drivewxmini.mapper.AppointmentDuringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/wxmini/teacher")
public class AppointmentDuringController {

    private final Logger logger = LoggerFactory.getLogger(AppointmentClassController.class);

    @Autowired
    private WxAppointmentService wxAppointmentService;

    @Autowired
    private AppointmentDuringMapper appointmentDuringMapper;

    @RequestMapping(path="/appointmentDuringInfo", method= RequestMethod.GET)
    public ResponseResult appointmentDuringInfo(@RequestParam(value = "teacherId") Integer teacherId) {
        List<AppointmentDuring> appointmentDuringList = wxAppointmentService.getAppointmentDuring(teacherId);
        return ResponseResult.success(ResponseCode.SUCCESS, appointmentDuringList);
    }

    @RequestMapping(path="/appointmentDuringDelete/{ids}", method= RequestMethod.DELETE)
    public ResponseResult appointmentDuringDelete(@PathVariable String ids) {
        appointmentDuringMapper.deleteByIds(ids);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    // TODO 只能插入12条数据
    @RequestMapping(path="/appointmentDuringSave", method= RequestMethod.POST)
    public ResponseResult appointmentDuringSave(@RequestBody @Validated AppointmentDuring appointmentDuring) {
        appointmentDuringMapper.insertSelective(appointmentDuring);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    /**
     * 一键生成默认时间段
     * */
    @RequestMapping(path="/generateDuring", method= RequestMethod.GET)
    public ResponseResult generateDuring(@RequestParam(value = "teacherId") Integer teacherId) {
        List<AppointmentDuring> duringList = new ArrayList<>();
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(8, 0, 0), LocalTime.of(8, 30, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(8, 30, 0), LocalTime.of(9, 0, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(9, 0, 0), LocalTime.of(9, 30, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(9, 30, 0), LocalTime.of(10, 0, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(10, 0, 0), LocalTime.of(10, 30, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(10, 30, 0), LocalTime.of(11, 0, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(13, 30, 0), LocalTime.of(14, 0, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(14, 0, 0), LocalTime.of(14, 30, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(14, 30, 0), LocalTime.of(15, 0, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(15, 0, 0), LocalTime.of(15, 30, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(15, 30, 0), LocalTime.of(16, 0, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(16, 0, 0), LocalTime.of(16, 30, 0), false));
        duringList.add(new AppointmentDuring(null, teacherId, LocalTime.of(16, 30, 0), LocalTime.of(17, 0, 0), false));
        Iterator<AppointmentDuring> iter = duringList.iterator();
        while(iter.hasNext()){
            appointmentDuringMapper.insertSelective(iter.next());
        }
        return ResponseResult.success(ResponseCode.SUCCESS, duringList);
    }
}
