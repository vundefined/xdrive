package com.wypaperplane.syscore;

import com.wypaperplane.syscore.utils.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class SysCoreTest {

    @Test
    void mTest() {
        Resource resource[] = new Resource[0];
        try {
            resource = new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Resource resource1 : resource) {
            try {
                System.out.println("----sssss");
                System.out.println(resource1.getURI().getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void ymlTest() throws IOException {
        InputStream inputStream = new ClassPathResource("application-core-prod.yml").getInputStream();
        Yaml yaml = new Yaml();
        Map<String, Object> objectMap = yaml.load(inputStream);
        Map<String, Object> nginxMap = (Map<String, Object>) objectMap.get("nginx");
        System.out.println(nginxMap.get("path"));
        System.out.println("Hello");
    }
}
