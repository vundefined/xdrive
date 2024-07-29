package com.wypaperplane.drivewxmini.vo;

import com.wypaperplane.drivewxmini.entity.TeacherCar;
import com.wypaperplane.drivewxmini.entity.TeacherJob;
import com.wypaperplane.drivewxmini.entity.WxUserBase;

import java.util.List;

/**
 * 学员查看教练详细信息 TeacherOfStudent
 * */
public class TeacherDetailVo {

    /**
    * wx_user_id
    * */
    private Integer id;

    private WxUserBase wxUserBase;

    private TeacherJob teacherJob;

    private List<TeacherCar> teacherCarList;

    public TeacherDetailVo() {}

    public TeacherDetailVo(Integer id, WxUserBase wxUserBase, TeacherJob teacherJob, List<TeacherCar> teacherCarList) {
        this.id = id;
        this.wxUserBase = wxUserBase;
        this.teacherJob = teacherJob;
        this.teacherCarList = teacherCarList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WxUserBase getWxUserBase() {
        return wxUserBase;
    }

    public void setWxUserBaseVo(WxUserBase wxUserBase) {
        this.wxUserBase = wxUserBase;
    }

    public TeacherJob getTeacherJob() {
        return teacherJob;
    }

    public void setTeacherJob(TeacherJob teacherJob) {
        this.teacherJob = teacherJob;
    }

    public List<TeacherCar> getTeacherCarList() {
        return teacherCarList;
    }

    public void setTeacherCarList(List<TeacherCar> teacherCarList) {
        this.teacherCarList = teacherCarList;
    }
}
