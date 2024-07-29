package com.wypaperplane.drivewxmini.mapper;

import com.wypaperplane.drivewxmini.entity.AppointmentRecord;
import com.wypaperplane.drivewxmini.vo.AppointmentRecordVo;
import com.wypaperplane.syscore.mapper.CustomMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentRecordMapper extends CustomMapper<AppointmentRecord> {

    List<AppointmentRecordVo> selectByStudent(@Param("studentId") Integer studentId);

    List<AppointmentRecordVo> selectByTeacher(@Param("teacherId") Integer teacherId, @Param("classId") Integer classId, @Param("localDateLike") String localDateLike);

    int updateStatus(@Param("teacherId") Integer teacherId, @Param("status") int status, @Param("addTimeLike") String addTimeLike, @Param("rejected") int rejected);
}
