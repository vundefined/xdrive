package com.wypaperplane.syscore;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseResult<T> {
    private static final ResponseResult responseResult = new ResponseResult();
    private int code;
    private String message;
    private T data;

    private ResponseResult(){}

    public static <T> ResponseResult<T> success(ResponseCode responseCode) {
        responseResult.setCode(responseCode.getCode());
        responseResult.setMessage(responseCode.getMessage());
        responseResult.setData(null);
        return responseResult;
    }

    public static <T> ResponseResult<T> success(ResponseCode responseCode, T data) {
        responseResult.setCode(responseCode.getCode());
        responseResult.setMessage(responseCode.getMessage());
        responseResult.setData(data);
        return responseResult;
    }

    public static <T> ResponseResult<T> successPage(ResponseCode responseCode, List<T> data) {
        PageInfo pageInfo = new PageInfo(data);
        Map<String, Object> dataKv = new HashMap<String, Object>();
        dataKv.put("list", pageInfo.getList());
        dataKv.put("total", pageInfo.getTotal());
        responseResult.setCode(responseCode.getCode());
        responseResult.setMessage(responseCode.getMessage());
        responseResult.setData(dataKv);
        return responseResult;
    }

    public static <T> ResponseResult<T> failed(ResponseCode responseCode) {
        responseResult.setCode(responseCode.getCode());
        responseResult.setMessage(responseCode.getMessage());
        responseResult.setData(null);
        return responseResult;
    }

    public static <T> ResponseResult<T> failed(int code, String message) {
        responseResult.setCode(code);
        responseResult.setMessage(message);
        responseResult.setData(null);
        return responseResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseUtil{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
