package com.wypaperplane.drivewxmini.service;

import com.wypaperplane.drivewxmini.entity.AppointmentClass;
import com.wypaperplane.drivewxmini.mapper.AppointmentClassDuringMapper;
import com.wypaperplane.drivewxmini.mapper.AppointmentClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentClassService {

    @Autowired
    private AppointmentClassMapper appointmentClassMapper;

    @Autowired
    private AppointmentClassDuringMapper appointmentClassDuringMapper;

    public void mDelete(String ids) {
        List<AppointmentClass> appointmentClassList = appointmentClassMapper.selectByIds(ids);
        List<Integer> classIdList = appointmentClassList.stream().map(AppointmentClass::getClassId).collect(Collectors.toList());
        // 先删除 appointment_class_during 表，再删除 appointment_class 表, 以保持数据同步
        appointmentClassDuringMapper.deleteByClassIds(appointmentClassList.get(0).getWxUserId(), classIdList);
        appointmentClassMapper.deleteByIds(ids);
    }
}
