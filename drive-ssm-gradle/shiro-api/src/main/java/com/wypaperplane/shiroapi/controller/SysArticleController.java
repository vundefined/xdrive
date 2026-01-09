package com.wypaperplane.shiroapi.controller;


import com.github.pagehelper.PageHelper;
import com.wypaperplane.shiroapi.entity.SysArticle;
import com.wypaperplane.shiroapi.mapper.SysArticleMapper;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class SysArticleController {

    @Autowired
    private SysArticleMapper sysArticleMapper;

    @RequestMapping(path="/sysArticleAdd", method= RequestMethod.POST)
    public ResponseResult sysMenuAdd(@RequestBody @Validated SysArticle sysArticle) {
        sysArticleMapper.insertSelective(sysArticle);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="/sysArticleUpdate", method= RequestMethod.PUT)
    public ResponseResult sysMenuUpdate(@RequestBody @Validated SysArticle sysArticle) {
        sysArticleMapper.updateByPrimaryKeySelective(sysArticle);
        return ResponseResult.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(path="/sysArticlePage", method=RequestMethod.GET)
    public ResponseResult sysArticlePage(
            String title,
            @RequestParam(value = "curPage", defaultValue = "1") Integer curPage,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        PageHelper.startPage(curPage, limit);
        Example example = new Example(SysArticle.class);
        example.createCriteria().andEqualTo("title", title);
        List<SysArticle> sysArticleList = sysArticleMapper.selectByExample(example);
        return ResponseResult.successPage(ResponseCode.SUCCESS, sysArticleList);
    }

    @RequestMapping(path="/sysArticle", method=RequestMethod.GET)
    public ResponseResult sysArticle(@RequestParam Integer id){
        Example example = new Example(SysArticle.class);
        example.createCriteria().andEqualTo("id", id);
        SysArticle sysArticle = sysArticleMapper.selectByPrimaryKey(id);
        return ResponseResult.success(ResponseCode.SUCCESS, sysArticle);
    }
}
