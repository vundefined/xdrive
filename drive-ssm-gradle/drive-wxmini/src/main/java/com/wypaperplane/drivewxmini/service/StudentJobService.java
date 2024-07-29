package com.wypaperplane.drivewxmini.service;

import com.wypaperplane.drivewxmini.entity.StudentJob;
import com.wypaperplane.drivewxmini.mapper.StudentJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class StudentJobService {

    @Autowired
    private StudentJobMapper studentJobMapper;

    public List<StudentJob> mSelect(Integer studentId) {
        Example example = new Example(StudentJob.class,true, true);
        example.createCriteria().andEqualTo("studentId", studentId);
        example.orderBy("addTime").desc();
        List<StudentJob> studentJobList = studentJobMapper.selectByExample(example);
        return studentJobList;
    }

    /**
     * 先删除默认配置
     * */
    public void mDelete(StudentJob studentJob) {
        studentJobMapper.deleteByPrimaryKey(studentJob);
    }

    /**
     * 插入之前判断数据库是否存在该条数据
     * */
    public Boolean hasOwn(Integer studentId, Integer classId) {
        Example example = new Example(StudentJob.class,true, true);
        example.createCriteria().andEqualTo("studentId", studentId).andEqualTo("classId", classId);
        StudentJob studentJob = studentJobMapper.selectOneByExample(example);
        if (ObjectUtils.isEmpty(studentJob)) {
            return true;
        } else {
            return false;
        }
    }
}
