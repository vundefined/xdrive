package com.wypaperplane.shiroapi.controller;

import com.wypaperplane.shiroapi.entity.SysMenu;
import com.wypaperplane.shiroapi.entity.SysRole;
import com.wypaperplane.shiroapi.entity.SysUser;
import com.wypaperplane.shiroapi.vo.LoginVo;
import com.wypaperplane.shiroapi.service.SysRoleService;
import com.wypaperplane.shiroapi.vo.SysMenuVo;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/admin/auth")
public class SysAuthController {

    private final Logger logger = LoggerFactory.getLogger(SysAuthController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(path="/login", method= RequestMethod.POST)
    public ResponseResult login(@RequestBody @Validated LoginVo login) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(login.getUsername(), login.getPassword());
        currentUser.login(token);
        return ResponseResult.success(ResponseCode.SUCCESS, currentUser.getSession().getId());
    }

    @RequestMapping(path="/logout", method=RequestMethod.POST)
    public ResponseResult logout() {
        SecurityUtils.getSubject().logout();
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="/userInfo", method=RequestMethod.GET)
    public ResponseResult getUserInfo() {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<SysRole> sysRoleList = sysRoleService.getSysRoleList(sysUser.getRoleIds());
        Set<Integer> menuIdSet = sysRoleService.getMenuIdSet(sysRoleList);
        List<SysMenu> sysMenuList = sysRoleService.getSysMenuList(menuIdSet);
        List<SysMenuVo> sysMenuVoList = sysRoleService.buildTree(sysMenuList);

        Map<String, Object> userInfoMap = new LinkedHashMap<>();
        userInfoMap.put("userInfo", sysUser);
        userInfoMap.put("menuInfo", sysMenuVoList);
        return ResponseResult.success(ResponseCode.SUCCESS, userInfoMap);
    }

}
