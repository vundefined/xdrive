package com.wypaperplane.drivewxmini.mapper;

import com.wypaperplane.drivewxmini.entity.AppointmentClassDuring;
import com.wypaperplane.syscore.mapper.CustomMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentClassDuringMapper extends CustomMapper<AppointmentClassDuring> {

    void deleteByClassIds(@Param("wxUserId") Integer wxUserId, @Param("classIdList") List<Integer> classIdList);

}
