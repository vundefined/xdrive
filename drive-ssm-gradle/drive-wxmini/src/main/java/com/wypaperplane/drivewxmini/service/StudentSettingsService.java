package com.wypaperplane.drivewxmini.service;

import com.wypaperplane.drivewxmini.entity.StudentSettings;
import com.wypaperplane.drivewxmini.mapper.StudentSettingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class StudentSettingsService {

    @Autowired
    private StudentSettingsMapper studentSettingsMapper;

    /**
     * @param stype 0默认teacherId 1默认classId
     * */
    public StudentSettings mSelect(Integer studentId, Byte stype) {
        Example example = new Example(StudentSettings.class, true, true);
        example.createCriteria()
                .andEqualTo("studentId", studentId)
                .andEqualTo("stype", stype);
        StudentSettings studentSettings = studentSettingsMapper.selectOneByExample(example);
        return studentSettings;
    }

    public void mInsert(StudentSettings studentSettings) {
        studentSettingsMapper.save(studentSettings);
    }

    public void mDelete(Integer studentId, Byte stype) {
        StudentSettings studentSettings = this.mSelect(studentId, stype);
        if (!ObjectUtils.isEmpty(studentSettings)) {
            studentSettingsMapper.deleteByPrimaryKey(studentSettings);
        }
    }
}
