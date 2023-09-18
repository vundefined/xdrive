package com.auth0.jwt.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;

public interface JWTPartsParser {

    Payload parsePayload(String json) throws JWTDecodeException;

    Header parseHeader(String json) throws JWTDecodeException;
}
