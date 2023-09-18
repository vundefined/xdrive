package com.wypaperplane.drivewxmini.config;

import com.wypaperplane.drivewxmini.exceptions.WxCodeException;
import com.wypaperplane.syscore.ResponseResult;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wypaperplane.syscore.properties.MCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order
public class WxminiExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(WxminiExceptionHandle.class);

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseBody
    public ResponseResult tokenEmptyExceptionHandler(JWTVerificationException e) {
        logger.error(String.valueOf(e));
        return ResponseResult.failed(MCommon.RESPONSECODE_COMMON, e.getMessage());
    }

    @ExceptionHandler(WxCodeException.class)
    @ResponseBody
    public ResponseResult wxCodeExceptionHandler(WxCodeException e) {
        logger.error(String.valueOf(e));
        return ResponseResult.failed(MCommon.RESPONSECODE_COMMON, e.getMessage());
    }
}
