package com.auth0.jwt.interfaces;

public interface Header {

    String getAlgorithm();

    String getType();


    String getContentType();

    String getKeyId();

    Claim getHeaderClaim(String name);
}
