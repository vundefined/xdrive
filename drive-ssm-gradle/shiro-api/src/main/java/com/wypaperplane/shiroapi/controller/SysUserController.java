package com.wypaperplane.shiroapi.controller;

import com.github.pagehelper.PageHelper;
import com.wypaperplane.syscore.entity.SysUser;
import com.wypaperplane.syscore.mapper.SysUserMapper;
import com.wypaperplane.shiroapi.shiro.AdminByteSource;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.utils.NanoidUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class SysUserController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @RequestMapping(path="/sysUserAdd", method= RequestMethod.POST)
    public ResponseResult sysUserAdd(@RequestBody @Validated SysUser sysUser) {
        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPassword = new SimpleHash("MD5", sysUser.getPassword(), new AdminByteSource(sysUser.getUsername()), 4).toHex();
        sysUser.setId(NanoidUtil.generateNanoidId(8));
        sysUser.setPassword(newPassword);
        sysUser.setDeleted(false);
        sysUserMapper.insertSelective(sysUser);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="/sysUserDeleteById/{ids}", method=RequestMethod.DELETE)
    public ResponseResult sysUserDeleteById(@PathVariable Integer[] ids){
        Example example = new Example(SysUser.class);
        example.createCriteria().andIn("id", Arrays.asList(ids));
        sysUserMapper.deleteByExample(example);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    /*
    * 管理员更新人员信息
    * */
    @RequestMapping(path="/sysUserUpdate", method=RequestMethod.PUT)
    public ResponseResult sysUserUpdate(@RequestBody @Validated SysUser sysUser) {
        String newPassword = new SimpleHash("MD5", sysUser.getPassword(), new AdminByteSource(sysUser.getUsername()), 4).toHex();
        sysUser.setPassword(newPassword);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="/sysUserPage", method=RequestMethod.GET)
    public ResponseResult sysUserPage(String username,
            @RequestParam(value = "curPage", defaultValue = "1") Integer curPage,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        PageHelper.startPage(curPage, limit);
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("username", username);
        List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
        return ResponseResult.successPage(ResponseCode.SUCCESS, sysUserList);
    }
}
