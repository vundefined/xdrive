package com.wypaperplane.drivewxmini.config;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wypaperplane.syscore.config.SysCoreConfig;
import com.wypaperplane.syscore.properties.WxPayProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

public class MSpringConfig {

    private final Logger logger = LoggerFactory.getLogger(MSpringConfig.class);

    public MSpringConfig() {
        logger.info("SpringConfig---");
    }

    @Bean
    public Config wxPayConfig(WxPayProperties wxPayProperties) {
        Config config = new RSAAutoCertificateConfig.Builder()
                .merchantId(wxPayProperties.getMerchantId())
                .privateKeyFromPath(wxPayProperties.getPrivateKeyPath())
                .merchantSerialNumber(wxPayProperties.getMerchantSerialNumber())
                .apiV3Key(wxPayProperties.getApiV3key())
                .build();
        return config;
    }
}
