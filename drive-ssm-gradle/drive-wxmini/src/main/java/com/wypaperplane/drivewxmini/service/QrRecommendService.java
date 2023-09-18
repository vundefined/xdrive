package com.wypaperplane.drivewxmini.service;

import com.wypaperplane.syscore.entity.QrRecommend;
import com.wypaperplane.syscore.mapper.QrRecommendMapper;
import com.wypaperplane.syscore.vo.QrRecommendVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class QrRecommendService {

    private final Logger logger = LoggerFactory.getLogger(QrRecommendService.class);

    @Autowired
    private QrRecommendMapper qrRecommendMapper;

    public List<QrRecommend> mSelect(Integer teacherId) {
        Example example = new Example(QrRecommend.class,true, true);
        example.createCriteria().andEqualTo("teacherId", teacherId);
        List<QrRecommend> qrRecommendList = qrRecommendMapper.selectByExample(example);
        return qrRecommendList;
    }

    public QrRecommend mSelect(Integer teacherId, Integer classId) {
        Example example = new Example(QrRecommend.class,true, true);
        example.createCriteria().andEqualTo("teacherId", teacherId).andEqualTo("classId", classId);
        QrRecommend qrRecommend = qrRecommendMapper.selectOneByExample(example);
        return qrRecommend;
    }

    public List<QrRecommendVo> mSelectRecommendAndClass(Integer teacherId) {
        return qrRecommendMapper.selectByTeacher(teacherId);
    }

    public void mInsert(QrRecommend qrRecommend) {
        qrRecommendMapper.save(qrRecommend);
    }
}
