package com.wypaperplane.drivewxmini.wxpayments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wypaperplane.syscore.entity.ReceiversRes;

import java.util.List;

public class ProfitsharingResponse {

    @JsonProperty("sub_mchid")
    private String subMchid;

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


    /**
     * 微信分账单号
     * */
    @JsonProperty("order_id")
    private String orderId;

    private ProSharingStateEnum state;

    private List<ReceiversRes> receivers;

    public enum ProSharingStateEnum {
        PROCESSING, FINISHED
    }

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ProSharingStateEnum getState() {
        return state;
    }

    public void setState(ProSharingStateEnum state) {
        this.state = state;
    }

    public List<ReceiversRes> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<ReceiversRes> receivers) {
        this.receivers = receivers;
    }
}
