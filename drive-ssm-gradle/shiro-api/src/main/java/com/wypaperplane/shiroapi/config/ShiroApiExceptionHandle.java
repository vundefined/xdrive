package com.wypaperplane.shiroapi.config;

import com.wypaperplane.syscore.ResponseResult;
import com.wypaperplane.syscore.properties.MCommon;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order
public class ShiroApiExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(ShiroApiExceptionHandle.class);

    @ExceptionHandler(UnknownSessionException.class)
    @ResponseBody
    public ResponseResult shiroExceptionHandler(UnknownSessionException e) {
        logger.error(String.valueOf(e));
        return ResponseResult.failed(MCommon.RESPONSECODE_COMMON, e.getMessage());
    }
}
