package com.wypaperplane.syscore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceiversRes extends ReceiversReq{

    /**
     * 分账结果
     * */
    private String 	result;

    /**
     * 分账失败原因
     * */
    @JsonProperty("fail_reason")
    private String failReason;

    /**
     * 分账明细单号
     * */
    @JsonProperty("detail_id")
    private String detailId;

    /**
     * 分账创建时间
     * */
    @JsonProperty("create_time")
    private String createTime;

    /**
     * 分账完成时间
     * */
    @JsonProperty("finish_time")
    private String finishTime;

    public ReceiversRes() {}

    public ReceiversRes(String result, String failReason, String detailId, String createTime, String finishTime) {
        this.result = result;
        this.failReason = failReason;
        this.detailId = detailId;
        this.createTime = createTime;
        this.finishTime = finishTime;
    }

    public ReceiversRes(String type, String account, int amount, String description, String result, String failReason, String detailId, String createTime, String finishTime) {
        super(type, account, amount, description);
        this.result = result;
        this.failReason = failReason;
        this.detailId = detailId;
        this.createTime = createTime;
        this.finishTime = finishTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
