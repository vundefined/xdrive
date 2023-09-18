package com.wypaperplane.syscore.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxPayProperties {

    /** 商户号 */
    @Value("${wxpay.merchantId}")
    private String merchantId;

    /** 商户API私钥路径 */
    @Value("${wxpay.privateKeyPath}")
    private String privateKeyPath;

    /** 商户APIV3密钥 */
    @Value("${wxpay.apiV3key}")
    private String apiV3key;

    /** 商户证书序列号 */
    @Value("${wxpay.merchantSerialNumber}")
    private String merchantSerialNumber;

    @Value("${wxpay.notifyUrl}")
    private String notifyUrl;

    public WxPayProperties() {}

    public WxPayProperties(String merchantId, String privateKeyPath, String apiV3key, String merchantSerialNumber, String notifyUrl) {
        this.merchantId = merchantId;
        this.privateKeyPath = privateKeyPath;
        this.apiV3key = apiV3key;
        this.merchantSerialNumber = merchantSerialNumber;
        this.notifyUrl = notifyUrl;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getApiV3key() {
        return apiV3key;
    }

    public void setApiV3key(String apiV3key) {
        this.apiV3key = apiV3key;
    }

    public String getMerchantSerialNumber() {
        return merchantSerialNumber;
    }

    public void setMerchantSerialNumber(String merchantSerialNumber) {
        this.merchantSerialNumber = merchantSerialNumber;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
