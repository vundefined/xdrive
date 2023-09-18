package com.wypaperplane.syscore.config;

import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;

import com.wypaperplane.syscore.properties.MCommon;
import jakarta.validation.ConstraintViolationException;
import net.sf.jsqlparser.util.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
@Order
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult seriousHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseResult.failed(ResponseCode.SERIOUS);
    }

    /*
    * 非法 参数
    * */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseResult illegalArgumentHandler(IllegalArgumentException e) {
        logger.error(String.valueOf(e));
        return ResponseResult.failed(MCommon.RESPONSECODE_COMMON, e.getMessage());
    }

    /*
    * 方法参数不匹配
    * */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseResult methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException e) {
        logger.error(String.valueOf(e));
        return ResponseResult.failed(MCommon.RESPONSECODE_COMMON, e.getMessage());
    }

    /*
     * 方法参数不匹配
     * */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseResult httpMessageNotReadableHandler(HttpMessageNotReadableException e) {
        logger.error(String.valueOf(e));
        return ResponseResult.failed(MCommon.RESPONSECODE_COMMON, e.getMessage());
    }

    /*
     * 方法参数不匹配
     * */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseResult dataIntegrityViolationHandler(DataIntegrityViolationException e) {
        logger.error(String.valueOf(e));
        return ResponseResult.failed(MCommon.RESPONSECODE_COMMON, e.getMessage());
    }

    /*
     * 方法参数 验证失败
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        logger.error(String.valueOf(e)); // e.getMessage()
        return ResponseResult.failed(MCommon.RESPONSECODE_COMMON, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /*
     * 验证失败
     * */
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseResult validationHandler(ValidationException e) {
        logger.error(e.getMessage(), e);
        return ResponseResult.failed(ResponseCode.VALIDATE_FAILED);
    }

    /*
     * 约束条件 违规
     * */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseResult constraintViolationHandler(ConstraintViolationException e) {
        logger.error(String.valueOf(e));
        return ResponseResult.failed(MCommon.RESPONSECODE_COMMON, e.getMessage());
    }

    /*
     * Duplicate 重复
     * */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResponseResult duplicateKeyHandler(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return ResponseResult.failed(ResponseCode.DUPLICATE_KEY);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseResult requestParameterHandler(MissingServletRequestParameterException e) {
        logger.error(e.getMessage(), e);
        return ResponseResult.failed(MCommon.RESPONSECODE_COMMON, e.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseResult uploadSizeHandler(MaxUploadSizeExceededException e) {
        logger.error(e.getMessage(), e);
        return ResponseResult.failed(ResponseCode.FILESIZEBIG);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseResult requestMethodHandler(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage(), e);
        return ResponseResult.failed(ResponseCode.NOT_SUPPORTED);
    }
}
