package com.wypaperplane.shiroapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    private final Logger logger = LoggerFactory.getLogger(CorsConfig.class);

    private long maxAge = 30 * 24 * 60 * 60; // 当前跨域请求最大有效时长。这里默认30天

    public CorsConfig() {
        logger.info("CorsConfig---");
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration()); // 4 对接口配置跨域设置
        return new CorsFilter(source);

        /*FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CorsFilter(source));
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;*/
    }

    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true); // 允许cookies跨域
        corsConfiguration.addAllowedOrigin("http://localhost:8087"); // 1 设置访问源地址 http://localhost:8086
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        corsConfiguration.setMaxAge(maxAge); // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        return corsConfiguration;
    }
}
