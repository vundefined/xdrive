package com.wypaperplane.drivewxmini.mapper;

import com.wypaperplane.drivewxmini.entity.TeacherOfStudent;
import com.wypaperplane.drivewxmini.vo.TeacherDetailVo;
import com.wypaperplane.drivewxmini.vo.TeacherOfStudentVo;
import com.wypaperplane.syscore.mapper.CustomMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherOfStudentMapper extends CustomMapper<TeacherOfStudent> {

    List<TeacherOfStudentVo> studentBase(@Param("teacherId") Integer teacherId);

    List<TeacherOfStudentVo> teacherBase(@Param("studentId") Integer studentId);

    TeacherDetailVo teacherDetail(@Param("teacherId") Integer teacherId);
}
