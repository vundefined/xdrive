package com.wypaperplane.drivewxmini.config;

import com.wypaperplane.syscore.config.SysCoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Import({
        SysCoreConfig.class,
        RabbitmqConfig.class,
        MybatisConfig.class
})
@ComponentScan(basePackages = {
        "com.wypaperplane.drivewxmini.service",
        "com.wypaperplane.drivewxmini.properties"
}, basePackageClasses = {
        WxminiExceptionHandle.class
})
public class MSpringConfig {

    private final Logger logger = LoggerFactory.getLogger(MSpringConfig.class);

    public MSpringConfig() {
        logger.info("SpringConfig---");
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
