package com.wypaperplane.app.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

    @GetMapping("/doanloadFile")
    @ResponseBody
    public ResponseEntity<byte[]> doanloadFile() throws IOException {
        // 读取文件
        Path filePath = Paths.get("C:\\Users\\92487\\devlop\\note\\README.md");
        byte[] fileBytes = Files.readAllBytes(filePath);

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "file.ext"); // 可选，设置下载文件的名称

        // 返回字节数组作为响应体
        return ResponseEntity.ok()
                .headers(headers)
                .body(fileBytes);
    }
}
