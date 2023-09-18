package com.wypaperplane.syscore.entity;

import jakarta.validation.constraints.NotNull;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 与教练分账
 * */
public class OrderTeacher extends ReceiversRes implements Serializable, Cloneable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    private Integer id;

    @NotNull(message = "userId 不能为空字符串")
    private Integer teacherId;

    private Integer studentId;

    private String transactionId;

    private String outOrderNo;

    private String orderId;

    @Column(name = "`state`")
    private String state;

    public OrderTeacher() {}

    public OrderTeacher(Integer id, Integer teacherId, Integer studentId, String transactionId, String outOrderNo, String orderId, String state) {
        this.id = id;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.transactionId = transactionId;
        this.outOrderNo = outOrderNo;
        this.orderId = orderId;
        this.state = state;
    }

    public OrderTeacher(String type, String account, int amount, String description, String result, String failReason, String detailId, String createTime, String finishTime, Integer id, Integer teacherId, Integer studentId, String transactionId, String outOrderNo, String orderId, String state) {
        super(type, account, amount, description, result, failReason, detailId, createTime, finishTime);
        this.id = id;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.transactionId = transactionId;
        this.outOrderNo = outOrderNo;
        this.orderId = orderId;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
}
