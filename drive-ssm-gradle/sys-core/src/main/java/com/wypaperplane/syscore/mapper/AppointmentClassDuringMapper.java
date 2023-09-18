package com.wypaperplane.syscore.mapper;

import com.wypaperplane.syscore.entity.AppointmentClassDuring;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentClassDuringMapper extends CustomMapper<AppointmentClassDuring> {

    void deleteByClassIds(@Param("wxUserId") Integer wxUserId, @Param("classIdList") List<Integer> classIdList);

}
