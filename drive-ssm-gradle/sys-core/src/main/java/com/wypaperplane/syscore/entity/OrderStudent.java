package com.wypaperplane.syscore.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import java.io.Serializable;

public class OrderStudent implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "studentId 为空或类型错误")
    private Integer studentId;

    @NotNull(message = "teacherId 为空或类型错误")
    private Integer teacherId;

    /**
     * 交易的商品
     * */
    @NotNull(message = "classId 为空或类型错误")
    private Integer classId;

    private String outTradeNo;

    private String transactionId;

    private String tradeState;

    private String tradeStateDesc;

    private String successTime;

    private Integer total;

    private Integer payerTotal;

    private String currency;

    private String payerCurrency;

    /**
     * 是否完成分账, 0false, 1true
     * */
    private Boolean isProfitsharing;

    public OrderStudent() {}

    public OrderStudent(Integer id, Integer studentId, Integer teacherId, Integer classId, String outTradeNo, String transactionId, String tradeState, String tradeStateDesc, String successTime, Integer total, Integer payerTotal, String currency, String payerCurrency, Boolean isProfitsharing) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.classId = classId;
        this.outTradeNo = outTradeNo;
        this.transactionId = transactionId;
        this.tradeState = tradeState;
        this.tradeStateDesc = tradeStateDesc;
        this.successTime = successTime;
        this.total = total;
        this.payerTotal = payerTotal;
        this.currency = currency;
        this.payerCurrency = payerCurrency;
        this.isProfitsharing = isProfitsharing;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPayerTotal() {
        return payerTotal;
    }

    public void setPayerTotal(Integer payerTotal) {
        this.payerTotal = payerTotal;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayerCurrency() {
        return payerCurrency;
    }

    public void setPayerCurrency(String payerCurrency) {
        this.payerCurrency = payerCurrency;
    }

    public Boolean getProfitsharing() {
        return isProfitsharing;
    }

    public void setProfitsharing(Boolean profitsharing) {
        isProfitsharing = profitsharing;
    }

    @Override
    public String toString() {
        return "OrderStudent{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", teacherId=" + teacherId +
                ", classId=" + classId +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", tradeState='" + tradeState + '\'' +
                ", tradeStateDesc='" + tradeStateDesc + '\'' +
                ", successTime='" + successTime + '\'' +
                ", total=" + total +
                ", payerTotal=" + payerTotal +
                ", currency='" + currency + '\'' +
                ", payerCurrency='" + payerCurrency + '\'' +
                ", isProfitsharing=" + isProfitsharing +
                '}';
    }
}
