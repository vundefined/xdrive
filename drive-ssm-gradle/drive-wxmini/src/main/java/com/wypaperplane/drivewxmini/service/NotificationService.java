package com.wypaperplane.drivewxmini.service;

import com.wypaperplane.drivewxmini.entity.Notification;
import com.wypaperplane.drivewxmini.mapper.NotificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class NotificationService {

    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationMapper notificationMapper;

    public Notification mSelect(Byte nType) {
        Example example = new Example(Notification.class, true, true);
        example.createCriteria().andEqualTo("nType", nType);
        Notification notification = notificationMapper.selectOneByExample(example);
        return notification;
    }
}
