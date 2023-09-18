package com.wypaperplane.adminapi.config;

import com.wypaperplane.syscore.config.SysCoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({
        WebSecurityConfig.class,
        SysCoreConfig.class
})
@ComponentScan(basePackages = {
        "com.wypaperplane.adminapi.service",
        "com.wypaperplane.adminapi.filter"
})
public class SpringConfig {

    private final Logger logger = LoggerFactory.getLogger(SpringConfig.class);

    public SpringConfig() {
        logger.info("SpringConfig---");
    }
}
