package com.wypaperplane.drivewxmini.wxpayments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wypaperplane.syscore.entity.ReceiversReq;

import java.util.List;

public class ProfitsharingRequest {

    private String appid;

    /**
     * 微信订单号
     * */
    @JsonProperty("transaction_id")
    private String transactionId;

    /**
     * 商户分账单号
     * */
    @JsonProperty("out_order_no")
    private String outOrderNo;

    private List<ReceiversReq> receivers;

    /**
     * 1、如果为true，该笔订单剩余未分账的金额会解冻回分账方商户；
     * 2、如果为false，该笔订单剩余未分账的金额不会解冻回分账方商户，可以对该笔订单再次进行分账。
     * */
    @JsonProperty("unfreeze_unsplit")
    private Boolean unfreezeUnsplit;

    public ProfitsharingRequest() {}

    public ProfitsharingRequest(String appid, String transactionId, String outOrderNo, List<ReceiversReq> receivers, Boolean unfreezeUnsplit) {
        this.appid = appid;
        this.transactionId = transactionId;
        this.outOrderNo = outOrderNo;
        this.receivers = receivers;
        this.unfreezeUnsplit = unfreezeUnsplit;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public List<ReceiversReq> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<ReceiversReq> receivers) {
        this.receivers = receivers;
    }

    public Boolean getUnfreezeUnsplit() {
        return unfreezeUnsplit;
    }

    public void setUnfreezeUnsplit(Boolean unfreezeUnsplit) {
        this.unfreezeUnsplit = unfreezeUnsplit;
    }
}
