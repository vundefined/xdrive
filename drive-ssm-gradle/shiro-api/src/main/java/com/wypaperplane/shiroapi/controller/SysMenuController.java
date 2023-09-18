package com.wypaperplane.shiroapi.controller;

import com.github.pagehelper.PageHelper;
import com.wypaperplane.shiroapi.vo.SysMenuVo;
import com.wypaperplane.syscore.entity.SysMenu;
import com.wypaperplane.syscore.entity.SysRole;
import com.wypaperplane.syscore.mapper.SysMenuMapper;
import com.wypaperplane.shiroapi.service.SysRoleService;
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
public class SysMenuController {

    private final Logger logger = LoggerFactory.getLogger(SysMenuController.class);

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * @param sysMenu
     * @return
     */
    @RequestMapping(path="/sysMenuAdd", method= RequestMethod.POST)
    public ResponseResult sysMenuAdd(@RequestBody @Validated SysMenu sysMenu) {
        sysMenuMapper.insertSelective(sysMenu);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="sysMenuDeleteById/{ids}", method=RequestMethod.DELETE)
    public ResponseResult sysMenuDeleteById(@PathVariable Integer[] ids){
        Example example = new Example(SysMenu.class);
        example.createCriteria().andIn("id", Arrays.asList(ids));
        sysMenuMapper.deleteByExample(example);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="sysMenuUpdate", method=RequestMethod.PUT)
    public ResponseResult sysMenuUpdate(@RequestBody @Validated SysMenu sysMenu) {
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="/sysMenuPage", method=RequestMethod.GET)
    public ResponseResult sysMenuPage(String title,
            @RequestParam(value = "curPage", defaultValue = "1") Integer curPage,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        PageHelper.startPage(curPage, limit);
        Example example = new Example(SysMenu.class);
        example.createCriteria().andEqualTo("title", title);
        List<SysMenu> sysMenuList = sysMenuMapper.selectByExample(example);
        return ResponseResult.successPage(ResponseCode.SUCCESS, sysMenuList);
    }

    @RequestMapping(path="/sysMenuTree", method=RequestMethod.GET)
    public ResponseResult sysMenuTree(String title) {
        Example example = new Example(SysMenu.class);
        example.createCriteria().andEqualTo("title", title);
        List<SysMenu> sysMenuList = sysMenuMapper.selectByExample(example);
        List<SysMenuVo> sysMenuVoList = sysRoleService.buildTree(sysMenuList);
        return ResponseResult.success(ResponseCode.SUCCESS, sysMenuVoList);
    }
}
