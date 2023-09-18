package com.wypaperplane.shiroapi.config;

import com.wypaperplane.syscore.config.SysCoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({
        SysCoreConfig.class,
        CorsConfig.class,
        ShiroConfig.class
})
@ComponentScan(basePackages = {
        "com.wypaperplane.shiroapi.service"
}, basePackageClasses = {
        ShiroApiExceptionHandle.class
})
public class SpringConfig {

    private final Logger logger = LoggerFactory.getLogger(SpringConfig.class);

    public SpringConfig() {
        logger.info("SpringConfig---");
    }
}
