package com.flamingo.iwrite.config;

import com.github.pagehelper.PageInterceptor;
import com.wypaperplane.syscore.mybatis.CustomEnumTypeHandler;
import com.wypaperplane.syscore.properties.MdatasourceProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

    private final Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

    public MybatisConfig() {
        logger.info("MybatisConfig----");
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource(MdatasourceProperties mdatasourceProperties) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(mdatasourceProperties.getDriver());
        driverManagerDataSource.setUrl(mdatasourceProperties.getUrl());
        driverManagerDataSource.setUsername(mdatasourceProperties.getUsername());
        driverManagerDataSource.setPassword(mdatasourceProperties.getPassword());
        return driverManagerDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DriverManagerDataSource driverManagerDataSource) throws Exception {
        Resource resource[] = new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(driverManagerDataSource);
        sqlSessionFactoryBean.setMapperLocations(resource);
        sqlSessionFactoryBean.setPlugins(new PageInterceptor());
        // Class<? extends TypeHandler> customEnumTypeHandler = CustomEnumTypeHandler.class;
        // sqlSessionFactoryBean.setDefaultEnumTypeHandler(customEnumTypeHandler);
        // sqlSessionFactoryBean.setDefaultEnumTypeHandler(EnumTypeHandler.class);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DriverManagerDataSource driverManagerDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(driverManagerDataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.wypaperplane.syscore.mapper.CustomMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "JDBC");

        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.flamingo.iwrite.mapper");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
