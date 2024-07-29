package com.wypaperplane.drivewxmini.service;

import com.wypaperplane.drivewxmini.entity.OrderStudent;
import com.wypaperplane.drivewxmini.mapper.OrderStudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class OrderStudentService {

    private final Logger logger = LoggerFactory.getLogger(OrderStudentService.class);

    @Autowired
    private OrderStudentMapper orderStudentMapper;

    public OrderStudent mSelect(Integer teacherId, Integer studentId, Integer classId) {
        Example example = new Example(OrderStudent.class, true, true);
        example.createCriteria()
                .andEqualTo("teacherId", teacherId)
                .andEqualTo("studentId", studentId)
                .andEqualTo("classId", classId);
        OrderStudent orderStudent = orderStudentMapper.selectOneByExample(example);
        return orderStudent;
    }

    public OrderStudent mSelect(Integer primaryId) {
        return orderStudentMapper.selectByPrimaryKey(primaryId);
    }
}
