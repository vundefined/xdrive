package com.wypaperplane.drivewxmini.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wypaperplane.drivewxmini.entity.QrInvite;
import com.wypaperplane.syscore.enumm.StorageCategory;
import com.wypaperplane.drivewxmini.mapper.QrInviteMapper;
import com.wypaperplane.syscore.utils.QrcodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;

@Service
public class QrInviteService {

    private final Logger logger = LoggerFactory.getLogger(QrInviteService.class);

    @Autowired
    private QrInviteMapper qrInviteMapper;

    public QrInvite mSelect(Integer teacherId) {
        Example example = new Example(QrInvite.class, true, true);
        example.createCriteria().andEqualTo("teacherId", teacherId);
        QrInvite qrInvite = qrInviteMapper.selectOneByExample(example);
        return qrInvite;
    }

    public String mInsert(QrInvite qrInvite) {
        /*
        扫普通链接二维码打开小程序 个人号无法使用此功能
        StringBuilder qrContent = new StringBuilder();
        qrContent.append("https://www.wypaperplane.com/wxstudent/?teacherId=");
        qrContent.append(qrInvite.getTeacherId());
        qrContent.append("&recommend=");
        qrContent.append(qrInvite.getTeacherId());
        qrContent.append("&qrType=0");
         */

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("teacherId", qrInvite.getTeacherId());
        objectNode.put("recommend", qrInvite.getTeacherId());
        objectNode.put("qrType", 0);
        String qrContent = objectNode.toString();

        String dirName = StorageCategory.QRCODE.getName();
        StringBuilder fileName = new StringBuilder();
        fileName.append("driveinvite").append(qrInvite.getTeacherId()).append(".png");
        QrcodeUtil.generateQrcode(qrContent, "驾驶预约教练邀请码", dirName, fileName.toString());

        LocalDateTime currentTime = LocalDateTime.now();
        StringBuilder qrImg = new StringBuilder();
        qrImg.append(dirName).append("/").append(fileName);
        qrInvite.setTeacherId(qrInvite.getTeacherId());
        qrInvite.setQrImg(qrImg.toString());
        qrInvite.setUpdateTime(currentTime);
        qrInviteMapper.save(qrInvite);
        return qrImg.toString();
    }
}
