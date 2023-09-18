package com.wypaperplane.syscore.entity;

public class ReceiversReq {

    /**
     * 分账接收方类型
     * */
    private String 	type;

    /**
     * 分账接收方账号
     * */
    private String account;

    /**
     * 分账金额
     * */
    private int amount;

    /**
     * 分账描述
     * */
    private String description;

    public ReceiversReq() {}

    public ReceiversReq(String type, String account, int amount, String description) {
        this.type = type;
        this.account = account;
        this.amount = amount;
        this.description = description;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
