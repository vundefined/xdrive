package com.wypaperplane.syscore.mapper;

import com.wypaperplane.syscore.entity.AppointmentRecord;
import com.wypaperplane.syscore.vo.AppointmentRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentRecordMapper extends CustomMapper<AppointmentRecord>{

    List<AppointmentRecordVo> selectByStudent(@Param("studentId") Integer studentId);

    List<AppointmentRecordVo> selectByTeacher(@Param("teacherId") Integer teacherId, @Param("classId") Integer classId, @Param("localDateLike") String localDateLike);

    int updateStatus(@Param("teacherId") Integer teacherId, @Param("status") int status, @Param("addTimeLike") String addTimeLike, @Param("rejected") int rejected);
}
