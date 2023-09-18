package com.wypaperplane.drivewxmini;

import com.wypaperplane.drivewxmini.config.MServletConfig;
import com.wypaperplane.drivewxmini.config.MSpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { MSpringConfig.class, MServletConfig.class})
public class WxMiniApiTest {

    private final Logger logger = LoggerFactory.getLogger(WxMiniApiTest.class);

    @Test
    void WxMiniApiTest() {
        // getBeans();
        System.out.println("WxMiniApiTest 你好");
    }

    void getBeans() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MSpringConfig.class);
        System.out.println(applicationContext.getBeanDefinitionCount()); // 获取容器中 Bean 的数量
        System.out.println(applicationContext.getBean("wxUserMapper"));

        for(String beansName : applicationContext.getBeanDefinitionNames()){ // 获取容器中所有 Bean 的名字
            System.out.println(beansName);
        }
    }
}
