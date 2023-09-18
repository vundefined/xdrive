package com.wypaperplane.drivewxmini.controller;

import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.service.LocalStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/wxmini/storage")
public class StorageController {

    private final Logger logger = LoggerFactory.getLogger(StorageController.class);

    @Autowired
    private LocalStorageService localStorageService;

    @RequestMapping(path="/create", method= RequestMethod.POST)
    public ResponseResult create(@RequestParam("file") MultipartFile multipartFile, @RequestParam("category") Integer category) {
        String fileName = localStorageService.uploadFile(multipartFile, category);
        return ResponseResult.success(ResponseCode.SUCCESS, fileName);
    }
}
