package com.auth0.jwt;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.impl.HeaderClaimsHolder;
import com.auth0.jwt.impl.HeaderSerializer;
import com.auth0.jwt.impl.PayloadClaimsHolder;
import com.auth0.jwt.impl.PayloadSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class JWTCreator {

    private final Algorithm algorithm;
    private final String headerJson;
    private final String payloadJson;

    private static final ObjectMapper mapper;
    private static final SimpleModule module;

    static {
        mapper = new ObjectMapper();
        module = new SimpleModule();
        module.addSerializer(PayloadClaimsHolder.class, new PayloadSerializer());
        module.addSerializer(HeaderClaimsHolder.class, new HeaderSerializer());
        mapper.registerModule(module);
        // mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        JsonMapper.builder().configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
    }

    public JWTCreator(Algorithm algorithm, Map<String, Object> headerClaims, Map<String, Object> payloadClaims) throws JWTCreationException {
        this.algorithm = algorithm;
        try {
            headerJson = mapper.writeValueAsString(new HeaderClaimsHolder(headerClaims));
            payloadJson = mapper.writeValueAsString(new PayloadClaimsHolder(payloadClaims));
        } catch (JsonProcessingException e) {
            throw new JWTCreationException("Some of the Claims couldn't be converted to a valid JSON format.", e);
        }
    }

    public String sign() throws SignatureGenerationException {
        String header = Base64.getUrlEncoder().withoutPadding().encodeToString(headerJson.getBytes(StandardCharsets.UTF_8));
        String payload = Base64.getUrlEncoder().withoutPadding().encodeToString(payloadJson.getBytes(StandardCharsets.UTF_8));

        byte[] signatureBytes = algorithm.sign(header.getBytes(StandardCharsets.UTF_8), payload.getBytes(StandardCharsets.UTF_8));
        String signature = Base64.getUrlEncoder().withoutPadding().encodeToString((signatureBytes));

        return String.format("%s.%s.%s", header, payload, signature);
    }
}
