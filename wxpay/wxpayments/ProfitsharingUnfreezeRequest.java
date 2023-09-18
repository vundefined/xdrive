package com.wypaperplane.drivewxmini.wxpayments;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 解冻剩余资金
 * */
public class ProfitsharingUnfreezeRequest {

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

    private String description;

    public ProfitsharingUnfreezeRequest() {}

    public ProfitsharingUnfreezeRequest(String transactionId, String outOrderNo, String description) {
        this.transactionId = transactionId;
        this.outOrderNo = outOrderNo;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
