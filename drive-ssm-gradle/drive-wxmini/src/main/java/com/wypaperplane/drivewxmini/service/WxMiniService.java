package com.wypaperplane.drivewxmini.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.wypaperplane.syscore.properties.WxStudentProperties;
import com.wypaperplane.syscore.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class WxMiniService {

    private final Logger logger = LoggerFactory.getLogger(WxMiniService.class);

    private Map<Integer, Byte> recordQrInviteCount = new HashMap<>();

    @Autowired
    private WxStudentProperties wxStudentProperties;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
    * 用于控制加入数量
    * */
    public void putQrInviteCount(Integer teacherId, Byte count) {
        recordQrInviteCount.put(teacherId, count);
    }

    public Byte getQrInviteCount(Integer teacher) {
        return recordQrInviteCount.get(teacher);
    }

    public void wxAccessToken() {
        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=")
                .append(wxStudentProperties.getAppid())
                .append("&secret=")
                .append(wxStudentProperties.getSecret());
        JsonNode jsonNode = HttpUtil.mGet(url.toString());
        String accessToken = jsonNode.get("access_token").toString();
        long expiresIn = jsonNode.get("expires_in").asLong();
        redisTemplate.opsForValue().set("WxAccessToken", accessToken, expiresIn, TimeUnit.SECONDS);
    }
}
