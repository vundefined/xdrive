package com.wypaperplane.syscore.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxTeacherProperties {

    @Value("${wxteacher.appid}")
    private String appid;

    @Value("${wxteacher.secret}")
    private String secret;

    public WxTeacherProperties() {}

    public WxTeacherProperties(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
