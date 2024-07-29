package com.wypaperplane.syscore.service;

import com.wypaperplane.syscore.enumm.StorageCategory;
import com.wypaperplane.syscore.utils.McommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Service
public class LocalStorageService {

    private final Logger logger = LoggerFactory.getLogger(LocalStorageService.class);

    public String uploadFile(MultipartFile multipartFile, StorageCategory storageCategory) {
        if (multipartFile.getSize() < 1) {
            return null;
        }

        String dirName = storageCategory.getName();

        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;

        StringBuilder fileName = new StringBuilder();
        fileName.append(System.currentTimeMillis())
                .append("_")
                .append(new Random().nextInt(1000))
                .append(originalFilename.substring(originalFilename.lastIndexOf(".")));

        try {
            Path _path = Paths.get(McommonUtil.getDirPath(dirName), fileName.toString());
            multipartFile.transferTo(_path);
        } catch(IOException ioException) {
            logger.error(String.valueOf(ioException));
        }

        StringBuilder relativeFile = new StringBuilder();
        relativeFile.append(dirName).append("/").append(fileName);

        return relativeFile.toString();
    }
}
