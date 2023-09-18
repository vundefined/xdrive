package com.wypaperplane.drivewxmini.wxpayments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wypaperplane.syscore.entity.ReceiversRes;

import java.util.List;

public class ProfitsharingUnfreezeResponse {

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
     * 微信分账单号，微信支付系统返回的唯一标识
     * */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 分账单状态
     * */
    private String state;

    private List<ReceiversRes> receivers;

    public ProfitsharingUnfreezeResponse() {}

    public ProfitsharingUnfreezeResponse(String transactionId, String outOrderNo, String orderId, String state, List<ReceiversRes> receivers) {
        this.transactionId = transactionId;
        this.outOrderNo = outOrderNo;
        this.orderId = orderId;
        this.state = state;
        this.receivers = receivers;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ReceiversRes> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<ReceiversRes> receivers) {
        this.receivers = receivers;
    }

    @Override
    public String toString() {
        return "ProfitsharingUnfreezeResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", outOrderNo='" + outOrderNo + '\'' +
                ", orderId='" + orderId + '\'' +
                ", state='" + state + '\'' +
                ", receivers=" + receivers +
                '}';
    }
}
