package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.drivewxmini.entity.TeacherCar;
import com.wypaperplane.drivewxmini.mapper.TeacherCarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@RequestMapping("/wxmini/teacher")
public class TeacherCarController {

    private final Logger logger = LoggerFactory.getLogger(TeacherCarController.class);

    @Autowired
    private TeacherCarMapper teacherCarMapper;

    // TODO 每位教练最多只能新增4条数据
    @RequestMapping(path="/carAdd", method= RequestMethod.POST)
    public ResponseResult carAdd(@RequestBody @Validated TeacherCar teacherCar) {
        teacherCarMapper.insertSelective(teacherCar);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="carDeleteById/{id}", method=RequestMethod.DELETE)
    public ResponseResult carDeleteById(@PathVariable Integer id) {
        teacherCarMapper.deleteByPrimaryKey(id);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="/carList", method=RequestMethod.GET)
    public ResponseResult carList(@RequestParam(value = "teacherId") Integer teacherId) {
        Example example = new Example(TeacherCar.class,true, true);
        example.createCriteria().andEqualTo("wxUserId", teacherId);
        List<TeacherCar> teacherCars = teacherCarMapper.selectByExample(example);
        return ResponseResult.success(ResponseCode.SUCCESS, teacherCars);
    }
}
