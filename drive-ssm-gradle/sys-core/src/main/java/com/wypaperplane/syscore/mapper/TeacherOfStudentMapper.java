package com.wypaperplane.syscore.mapper;

import com.wypaperplane.syscore.entity.TeacherOfStudent;
import com.wypaperplane.syscore.entity.WxUserBase;
import com.wypaperplane.syscore.vo.TeacherDetailVo;
import com.wypaperplane.syscore.vo.TeacherOfStudentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherOfStudentMapper extends CustomMapper<TeacherOfStudent> {

    List<TeacherOfStudentVo> studentBase(@Param("teacherId") Integer teacherId);

    List<TeacherOfStudentVo> teacherBase(@Param("studentId") Integer studentId);

    TeacherDetailVo teacherDetail(@Param("teacherId") Integer teacherId);
}
