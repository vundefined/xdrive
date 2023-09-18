package com.wypaperplane.shiroapi.controller;

import com.github.pagehelper.PageHelper;
import com.wypaperplane.syscore.entity.SysRole;
import com.wypaperplane.syscore.mapper.SysRoleMapper;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class SysRoleController {

    private final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @RequestMapping(path="/sysRoleAdd", method=RequestMethod.POST)
    public ResponseResult sysRoleAdd(@RequestBody @Validated SysRole sysRole) {
        sysRoleMapper.insertSelective(sysRole);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="sysRoleDeleteById/{ids}", method=RequestMethod.DELETE)
    public ResponseResult sysRoleDeleteById(@PathVariable Integer[] ids){
        Example example = new Example(SysRole.class);
        example.createCriteria().andIn("id", Arrays.asList(ids));
        sysRoleMapper.deleteByExample(example);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="sysRoleUpdate", method=RequestMethod.PUT)
    public ResponseResult sysRoleUpdate(@RequestBody @Validated SysRole sysRole) {
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="/sysRolePage", method=RequestMethod.GET)
    public ResponseResult sysRolePage(String name,
            @RequestParam(value = "curPage", defaultValue = "1") Integer curPage,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        PageHelper.startPage(curPage, limit);
        Example example = new Example(SysRole.class);
        example.createCriteria().andEqualTo("name", name);
        List<SysRole> sysRoleList = sysRoleMapper.selectByExample(example);
        return ResponseResult.successPage(ResponseCode.SUCCESS, sysRoleList);
    }

    @RequestMapping(path="/sysRoleList", method=RequestMethod.GET)
    public ResponseResult sysRoleList(){
        return ResponseResult.success(ResponseCode.SUCCESS, sysRoleMapper.selectAll());
    }
}
