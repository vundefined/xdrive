package com.wypaperplane.drivewxmini.wxpayments;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 添加分账接收方 请求参数
 * */
public class ProfitsharingReceiversRequest {

    private String appid;

    private String type;

    private String account;

    @JsonProperty("relation_type")
    private String relationType;

    public ProfitsharingReceiversRequest() {}

    public ProfitsharingReceiversRequest(String appid, String type, String account, String relationType) {
        this.appid = appid;
        this.type = type;
        this.account = account;
        this.relationType = relationType;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }
}
