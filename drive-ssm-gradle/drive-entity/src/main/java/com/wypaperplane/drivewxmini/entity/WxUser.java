package com.wypaperplane.drivewxmini.entity;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class WxUser extends WxUserVo{

    private String openid;

    private String sessionKey;

    private LocalDateTime addTime;

    private LocalDateTime updatedTime;

    /**
     * 微信支付后台是否已添加了分账接收方记录标识,0false,1true
     * */
    private Boolean receivers;

    public WxUser() {}

    public WxUser(String openid, String sessionKey, LocalDateTime addTime, LocalDateTime updatedTime, Boolean receivers) {
        this.openid = openid;
        this.sessionKey = sessionKey;
        this.addTime = addTime;
        this.updatedTime = updatedTime;
        this.receivers = receivers;
    }

    public WxUser(Byte role, Byte permission, Boolean deleted, String openid, String sessionKey, LocalDateTime addTime, LocalDateTime updatedTime, Boolean receivers) {
        super(role, permission, deleted);
        this.openid = openid;
        this.sessionKey = sessionKey;
        this.addTime = addTime;
        this.updatedTime = updatedTime;
        this.receivers = receivers;
    }

    public WxUser(Integer id, String avatar, String nickname, String reallname, Byte gender, String mobile, Byte role, Byte permission, Boolean deleted, String openid, String sessionKey, LocalDateTime addTime, LocalDateTime updatedTime, Boolean receivers) {
        super(id, avatar, nickname, reallname, gender, mobile, role, permission, deleted);
        this.openid = openid;
        this.sessionKey = sessionKey;
        this.addTime = addTime;
        this.updatedTime = updatedTime;
        this.receivers = receivers;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getReceivers() {
        return receivers;
    }

    public void setReceivers(Boolean receivers) {
        this.receivers = receivers;
    }
}
