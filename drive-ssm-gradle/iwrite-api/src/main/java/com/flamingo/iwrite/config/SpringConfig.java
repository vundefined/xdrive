package com.flamingo.iwrite.config;

import com.wypaperplane.syscore.config.SysCoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({SysCoreConfig.class, MybatisConfig.class})
@ComponentScan(basePackages = {"com.flamingo.iwrite.filter", "com.flamingo.iwrite.service"})
public class SpringConfig {

    private final Logger logger = LoggerFactory.getLogger(SpringConfig.class);

    public SpringConfig() {
        logger.info("SpringConfig---");
    }

}
