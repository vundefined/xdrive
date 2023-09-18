package com.auth0.jwt.algorithms;

import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@SuppressWarnings("WeakerAccess")
public abstract class Algorithm {

    private final String name;
    private final String description;

    protected Algorithm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Algorithm HMAC256(String secret) throws IllegalArgumentException {
        return new HMACAlgorithm("HS256", "HmacSHA256", secret);
    }

    public static Algorithm HMAC384(String secret) throws IllegalArgumentException {
        return new HMACAlgorithm("HS384", "HmacSHA384", secret);
    }

    public static Algorithm HMAC512(String secret) throws IllegalArgumentException {
        return new HMACAlgorithm("HS512", "HmacSHA512", secret);
    }

    public abstract void verify(DecodedJWT jwt) throws SignatureVerificationException;

    public byte[] sign(byte[] headerBytes, byte[] payloadBytes) throws SignatureGenerationException {
        byte[] contentBytes = new byte[headerBytes.length + 1 + payloadBytes.length];
        System.arraycopy(headerBytes, 0, contentBytes, 0, headerBytes.length);
        contentBytes[headerBytes.length] = (byte) '.';
        System.arraycopy(payloadBytes, 0, contentBytes, headerBytes.length + 1, payloadBytes.length);
        return sign(contentBytes);
    }

    public abstract byte[] sign(byte[] contentBytes) throws SignatureGenerationException;

    public String getSigningKeyId() {
        return null;
    }

    public String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

}
