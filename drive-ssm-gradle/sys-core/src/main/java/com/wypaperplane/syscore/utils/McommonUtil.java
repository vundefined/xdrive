package com.wypaperplane.syscore.utils;

import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class McommonUtil {

    public static String getDirPath(String dirName) throws IOException {
        // InputStream inputStream = new ClassPathResource("application-core-dev.yml").getInputStream();
        InputStream inputStream = new ClassPathResource("application-core-prod.yml").getInputStream();
        Yaml yaml = new Yaml();
        Map<String, Object> objectMap = yaml.load(inputStream);
        Map<String, Object> nginxMap = (Map<String, Object>) objectMap.get("nginx");
        String path = (String) nginxMap.get("path");

        StringBuilder dirPath = new StringBuilder();
        dirPath.append(path);
        dirPath.append(File.separator);
        dirPath.append(dirName);

        File file = new File(dirPath.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return dirPath.toString();
    }
}
