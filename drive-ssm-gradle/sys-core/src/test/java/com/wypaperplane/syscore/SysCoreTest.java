package com.wypaperplane.syscore;

import com.wypaperplane.syscore.utils.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class SysCoreTest {

    @Test
    void mTest() {
        HttpUtil.mGet("https://devapi.qweather.com/v7/weather/now?location=101010100&key=a2ee06f58db14a26953d1223b87b3ac0");
    }

    @Test
    void ymlTest() throws IOException {
        InputStream inputStream = new ClassPathResource("application-core.yml").getInputStream();
        Yaml yaml = new Yaml();
        Map<String, Object> objectMap = yaml.load(inputStream);
        Map<String, Object> nginxMap = (Map<String, Object>) objectMap.get("nginx");
        System.out.println(nginxMap.get("path"));
        System.out.println("Hello");
    }
}
