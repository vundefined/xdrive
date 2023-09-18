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
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {
        "com.wypaperplane.syscore.service",
        "com.wypaperplane.syscore.properties"
}, basePackageClasses = {
        GlobalExceptionHandler.class
})
@Import({
        RedisConfig.class,
        MybatisConfig.class
})
@EnableCaching
public class SysCoreConfig {

    private final Logger logger = LoggerFactory.getLogger(SysCoreConfig.class);

    public SysCoreConfig() {
        logger.info("SysCoreConfig---");
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("application-core.yml"));
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

    // @Bean
    // public MessageConverter jsonMessageConverter(){return new Jackson2JsonMessageConverter();}
}

/*
List<String> cacheNames = new ArrayList<>();
cacheNames.add("cachea");
cacheManager.setCacheNames(cacheNames);

@Autowired
private CacheManager cacheManager;

wxMiniService.getDriveLicenseClassTree();
Cache cache = cacheManager.getCache("licenseClass");
System.out.println(cache);
if (!ObjectUtils.isEmpty(cache)) {
    Cache.ValueWrapper wrapper = cache.get("0");
    System.out.println(wrapper);
}
*/