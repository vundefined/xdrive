package com.wypaperplane.shiroapi;

import com.wypaperplane.shiroapi.config.SpringConfig;
import com.wypaperplane.shiroapi.config.WebMvcConfig;
import com.wypaperplane.syscore.utils.McommonUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.io.IOException;

public class ShrioApiInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ SpringConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ WebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceResponseEncoding(true);

        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter(); // put delete

        DelegatingFilterProxy shiroFilterProxy = new DelegatingFilterProxy();
        shiroFilterProxy.setTargetBeanName("shiroFilterFactoryBean");
        shiroFilterProxy.setTargetFilterLifecycle(true);

        DelegatingFilterProxy corsFilterProxy = new DelegatingFilterProxy();
        corsFilterProxy.setTargetBeanName("corsFilter");
        corsFilterProxy.setTargetFilterLifecycle(true);

        return new Filter[]{characterEncodingFilter, hiddenHttpMethodFilter, corsFilterProxy, shiroFilterProxy};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        String location = null;
        try {
            location = McommonUtil.getDirPath("drivetemp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long maxFileSize = 2097152; // 2M
        long maxRequestSize = 4194304; // 4M
        int fileSizeThreshold = 0;
        registration.setMultipartConfig(new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold));
    }
}
