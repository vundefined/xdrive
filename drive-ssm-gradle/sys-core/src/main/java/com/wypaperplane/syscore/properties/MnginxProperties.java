package com.wypaperplane.syscore.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MnginxProperties {

    @Value("${nginx.path}")
    private String path;

    public MnginxProperties() {}

    public MnginxProperties(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
