package com.wypaperplane.drivewxmini.service;

import com.wypaperplane.syscore.entity.*;
import com.wypaperplane.syscore.mapper.*;
import com.wypaperplane.syscore.vo.AppointmentRecordVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WxAppointmentService {

    private final Logger logger = LoggerFactory.getLogger(WxAppointmentService.class);

    @Autowired
    private DriveLicenseClassMapper driveLicenseClassMapper;

    @Autowired
    private AppointmentRuleMapper appointmentRuleMapper;

    @Autowired
    private AppointmentClassMapper appointmentClassMapper;

    @Autowired
    private AppointmentDuringMapper appointmentDuringMapper;

    @Autowired
    private AppointmentClassDuringMapper appointmentClassDuringMapper;

    @Autowired
    private AppointmentRecordMapper appointmentRecordMapper;

    @Cacheable(cacheNames = {"licenseClass"})
    public List<DriveLicenseClass> getDriveLicenseClassTree() {
        // driveLicenseClassMapper.selectTree(); 这种方法是 mybatis生成的 tree 结构
        List<DriveLicenseClass> driveLicenseClassList = driveLicenseClassMapper.selectAll();
        Map<Integer, List<DriveLicenseClass>> pidListMap = driveLicenseClassList.stream().collect(Collectors.groupingBy(DriveLicenseClass::getpId));
        driveLicenseClassList.stream().forEach(item -> item.setChildren(pidListMap.get(item.getId())));
        return pidListMap.get(0);
    }

    @Cacheable(cacheNames = {"licenseClassByPid"})
    public List<DriveLicenseClass> getDriveLicenseClassList() {
        Example classMapper = new Example(DriveLicenseClass.class,true, true);
        classMapper.createCriteria().andEqualTo("pId", 0);
        List<DriveLicenseClass> driveLicenseClassList = driveLicenseClassMapper.selectByExample(classMapper);
        return driveLicenseClassList;
    }

    /**
    * 根据 teacherId 查询该教练的预约规则
    * */
    public AppointmentRule getAppointmentRule(Integer teacherId) {
        Example example = new Example(AppointmentRule.class,true, true);
        example.createCriteria().andEqualTo("teacherId", teacherId);
        AppointmentRule appointmentRule = appointmentRuleMapper.selectOneByExample(example);
        if (ObjectUtils.isEmpty(appointmentRule)) {
            Integer restIndexDefault[] = {};
            LocalTime startTimeDefault = LocalTime.of(8, 0);
            LocalTime endTimeDefault = LocalTime.of(14, 0);
            appointmentRule = new AppointmentRule();
            appointmentRule.setRestIndex(restIndexDefault);
            appointmentRule.setStartTime(startTimeDefault);
            appointmentRule.setEndTime(endTimeDefault);
        }
        return appointmentRule;
    }

    public List<AppointmentClass> getAppointmentClass(Integer teacherId) {
        Example example = new Example(AppointmentClass.class,true, true);
        example.createCriteria().andEqualTo("wxUserId", teacherId);
        example.orderBy("classId").asc();
        List<AppointmentClass> appointmentClassList = appointmentClassMapper.selectByExample(example);
        if (appointmentClassList.isEmpty()) {
            appointmentClassList = new ArrayList<>();
        }
        return appointmentClassList;
    }

    /**
     * 插入之前判断数据库是否存在该条数据
     * */
    public Boolean getAppointmentClass(Integer teacherId, Integer classId) {
        Example example = new Example(AppointmentClass.class,true, true);
        example.createCriteria().andEqualTo("wxUserId", teacherId).andEqualTo("classId", classId);
        AppointmentClass appointmentClass = appointmentClassMapper.selectOneByExample(example);
        if (ObjectUtils.isEmpty(appointmentClass)) {
            return true;
        } else {
            return false;
        }
    }

    public List<AppointmentDuring> getAppointmentDuring(Integer teacherId) {
        Example example = new Example(AppointmentDuring.class,true, true);
        example.createCriteria().andEqualTo("wxUserId", teacherId);
        example.orderBy("startTime").asc();
        List<AppointmentDuring> appointmentDuringList = appointmentDuringMapper.selectByExample(example);
        if (appointmentDuringList.isEmpty()) {
            appointmentDuringList = new ArrayList<>();
        }
        return appointmentDuringList;
    }

    /**
    * Integer[] idsArr = {1, 2, 3, 4};
    * String idsStr = "1, 2, 3, 4";
    * */
    public List<AppointmentDuring> getAppointmentDuring(Integer[] ids) {
        return appointmentDuringMapper.selectByIds(StringUtils.arrayToCommaDelimitedString(ids));
    }

    public List<AppointmentClassDuring> getAppointmentClassDuring(Integer teacherId) {
        Example example = new Example(AppointmentClassDuring.class,true, true);
        example.createCriteria().andEqualTo("wxUserId", teacherId);
        List<AppointmentClassDuring> appointmentClassDuringList = appointmentClassDuringMapper.selectByExample(example);
        return appointmentClassDuringList;
    }

    public AppointmentClassDuring getAppointmentClassDuring(Integer teacherId, Integer classId) {
        Example example = new Example(AppointmentClassDuring.class,true, true);
        example.createCriteria()
                .andEqualTo("wxUserId", teacherId)
                .andEqualTo("classId", classId);
        return appointmentClassDuringMapper.selectOneByExample(example);
    }

    /**
    * 限制一位学员，同一种驾驶类型，一天只能预约一次
    * */
    public Boolean hasAppointmentToday(Integer studentId, Integer classId, LocalDate localDate) {
        Example example = new Example(AppointmentRecord.class,true, true);
        example.createCriteria()
                .andEqualTo("studentId", studentId)
                .andEqualTo("classId", classId)
                .andLike("addTime", localDate.toString() + "%");
        int count = appointmentRecordMapper.selectCountByExample(example);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *教师 和 学员 按当日条件 查看所有学员预约概览
     **/
    public List<Map<String, Object>> todayPreviewService(Integer teacherId) {
        LocalDate localDate = LocalDate.now();
        String localDateLike = localDate.toString() + "%";
        List<DriveLicenseClass> driveLicenseClassTree = getDriveLicenseClassTree();
        List<AppointmentClassDuring> appointmentClassDuringList = getAppointmentClassDuring(teacherId);
        List<AppointmentRecordVo> appointmentRecordVoList = appointmentRecordMapper.selectByTeacher(teacherId, null, localDateLike);
        Map<Integer, List<AppointmentRecordVo>> classIdListMap = appointmentRecordVoList.stream().collect(Collectors.groupingBy(AppointmentRecordVo::getClassId));

        List<Map<String, Object>> objectList = new ArrayList<>();
        appointmentClassDuringList.stream().forEach(item -> {
            Integer[] classIndex = item.getClassIndex();
            DriveLicenseClass driveLicenseClass = driveLicenseClassTree.get(classIndex[0]);
            String className = driveLicenseClass.getcName() + " - " + driveLicenseClass.getChildren().get(classIndex[1]).getcName();
            Map<String, Object> mMap = new LinkedHashMap<>();
            mMap.put("classId", item.getClassId());
            mMap.put("className", className);
            mMap.put("appointmentRecordVoList", classIdListMap.get(item.getClassId()));
            objectList.add(mMap);
        });
        return objectList;
    }
}
