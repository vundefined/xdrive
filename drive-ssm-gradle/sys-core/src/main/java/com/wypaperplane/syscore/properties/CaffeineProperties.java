package com.wypaperplane.syscore.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CaffeineProperties {

    private List<String> cacheNames;

    @Value("${caffeine.spec}")
    private String spec;

    public CaffeineProperties() {}

    public CaffeineProperties(List<String> cacheNames, String spec) {
        this.cacheNames = cacheNames;
        this.spec = spec;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public List<String> getCacheNames() {
        return cacheNames;
    }

    public void setCacheNames(List<String> cacheNames) {
        this.cacheNames = cacheNames;
    }
}
