package com.wypaperplane.drivewxmini.wxpayments;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 添加分账接收方 响应参数
 * */
public class ProfitsharingReceiversResponse {

    private String type;

    private String account;

    @JsonProperty("relation_type")
    private String relationType;

    public ProfitsharingReceiversResponse() {}

    public ProfitsharingReceiversResponse(String type, String account, String relationType) {
        this.type = type;
        this.account = account;
        this.relationType = relationType;
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

    @Override
    public String toString() {
        return "ProfitsharingReceiversResponse{" +
                "type='" + type + '\'' +
                ", account='" + account + '\'' +
                ", relationType='" + relationType + '\'' +
                '}';
    }
}
