package com.wypaperplane.drivewxmini.mapper;

import com.wypaperplane.drivewxmini.entity.QrRecommend;
import com.wypaperplane.drivewxmini.vo.QrRecommendVo;
import com.wypaperplane.syscore.mapper.CustomMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QrRecommendMapper extends CustomMapper<QrRecommend> {

    List<QrRecommendVo> selectByTeacher(@Param("teacherId") Integer teacherId);
}
