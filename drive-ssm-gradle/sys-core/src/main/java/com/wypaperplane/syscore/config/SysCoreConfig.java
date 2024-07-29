package com.wypaperplane.syscore.config;

import com.github.benmanes.caffeine.cache.CaffeineSpec;
import com.wypaperplane.syscore.properties.CaffeineProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {
        "com.wypaperplane.syscore.service",
        "com.wypaperplane.syscore.properties"
}, basePackageClasses = {GlobalExceptionHandler.class})
@Import({RedisConfig.class})
@EnableCaching
public class SysCoreConfig {

    private final Logger logger = LoggerFactory.getLogger(SysCoreConfig.class);

    public SysCoreConfig() {
        logger.info("SysCoreConfig---");
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("application-core-dev.yml"));
        // yamlPropertiesFactoryBean.setResources(new ClassPathResource("application-core-prod.yml"));
        Properties properties = yamlPropertiesFactoryBean.getObject();
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setProperties(properties);
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public CacheManager cacheManager(CaffeineProperties caffeineProperties) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeineSpec(CaffeineSpec.parse(caffeineProperties.getSpec()));
        return cacheManager;
    }
}

