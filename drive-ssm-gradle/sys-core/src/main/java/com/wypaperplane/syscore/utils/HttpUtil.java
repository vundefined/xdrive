package com.wypaperplane.syscore.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .callTimeout(180000, TimeUnit.SECONDS)
            .connectTimeout(180000, TimeUnit.SECONDS)
            .readTimeout(180000, TimeUnit.SECONDS)
            .readTimeout(180000, TimeUnit.SECONDS)
            .build();

    public static JsonNode mGet(String url) {
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                InputStream inputStream = response.body().byteStream();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(inputStream);
                return jsonNode;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static JsonNode mPost(String url, RequestBody formBody) {
        // RequestBody formBody = new FormBody.Builder().add("search", "Jurassic Park").build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                InputStream inputStream = response.body().byteStream();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(inputStream);
                return jsonNode;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
