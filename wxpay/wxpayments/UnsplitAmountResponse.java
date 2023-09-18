package com.wypaperplane.drivewxmini.wxpayments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnsplitAmountResponse {

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("unsplit_amount")
    private int unsplitAmount;

    public UnsplitAmountResponse() {}

    public UnsplitAmountResponse(String transactionId, int unsplitAmount) {
        this.transactionId = transactionId;
        this.unsplitAmount = unsplitAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getUnsplitAmount() {
        return unsplitAmount;
    }

    public void setUnsplitAmount(int unsplitAmount) {
        this.unsplitAmount = unsplitAmount;
    }

    @Override
    public String toString() {
        return "UnsplitAmountResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", unsplitAmount=" + unsplitAmount +
                '}';
    }
}
