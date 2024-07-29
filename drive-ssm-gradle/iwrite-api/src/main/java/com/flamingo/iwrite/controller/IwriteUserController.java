package com.flamingo.iwrite.controller;

import com.flamingo.iwrite.entity.IwriteUser;
import com.flamingo.iwrite.mapper.IwriteUserMapper;
import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/iwrite")
public class IwriteUserController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private IwriteUserMapper iwriteUserMapper;

    @RequestMapping(path="/userList", method = RequestMethod.GET)
    public ResponseResult userList() {
        List<IwriteUser> iwriteUserList = iwriteUserMapper.selectAll();
        return ResponseResult.success(ResponseCode.SUCCESS, iwriteUserList);
    }

    @RequestMapping(path="/userAdd", method= RequestMethod.POST)
    public ResponseResult userAdd(@RequestBody @Validated IwriteUser iwriteUser) {
        iwriteUserMapper.insertSelective(iwriteUser);
        return ResponseResult.success(ResponseCode.SUCCESS, iwriteUser);
    }
}
