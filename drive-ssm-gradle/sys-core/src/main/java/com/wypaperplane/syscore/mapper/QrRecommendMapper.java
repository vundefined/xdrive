package com.wypaperplane.syscore.mapper;

import com.wypaperplane.syscore.entity.QrRecommend;
import com.wypaperplane.syscore.vo.QrRecommendVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QrRecommendMapper extends CustomMapper<QrRecommend> {

    List<QrRecommendVo> selectByTeacher(@Param("teacherId") Integer teacherId);
}
