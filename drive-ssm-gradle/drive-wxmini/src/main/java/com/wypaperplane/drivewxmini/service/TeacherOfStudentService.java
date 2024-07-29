package com.wypaperplane.drivewxmini.service;

import com.wypaperplane.drivewxmini.entity.TeacherOfStudent;
import com.wypaperplane.drivewxmini.mapper.TeacherOfStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class TeacherOfStudentService {

    @Autowired
    private TeacherOfStudentMapper teacherOfStudentMapper;

    public TeacherOfStudent mSelect(Integer teacherId, Integer studentId) {
        Example example = new Example(TeacherOfStudent.class,true, true);
        example.createCriteria()
                .andEqualTo("teacherId", teacherId)
                .andEqualTo("studentId", studentId);
        TeacherOfStudent tos = teacherOfStudentMapper.selectOneByExample(example);
        return tos;
    }

    public void mDelete(TeacherOfStudent teacherOfStudent) {
        teacherOfStudentMapper.deleteByPrimaryKey(teacherOfStudent);
    }
}
