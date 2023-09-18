package com.auth0.jwt.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Claim {

    boolean isNull();

    boolean isMissing();

    Boolean asBoolean();

    Integer asInt();

    Long asLong();

    Double asDouble();

    String asString();

    Date asDate();

    default Instant asInstant() {
        Date date = asDate();
        return date != null ? date.toInstant() : null;
    }

    <T> T[] asArray(Class<T> clazz) throws JWTDecodeException;

    <T> List<T> asList(Class<T> clazz) throws JWTDecodeException;

    Map<String, Object> asMap() throws JWTDecodeException;

    <T> T as(Class<T> clazz) throws JWTDecodeException;
}
