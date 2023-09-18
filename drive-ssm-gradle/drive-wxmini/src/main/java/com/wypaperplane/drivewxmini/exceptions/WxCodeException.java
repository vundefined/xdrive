package com.wypaperplane.drivewxmini.exceptions;

public class WxCodeException extends RuntimeException {

    public WxCodeException(String message) {
        this(message, null);
    }

    public WxCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
