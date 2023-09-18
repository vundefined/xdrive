package com.auth0.jwt.interfaces;

import java.security.PrivateKey;
import java.security.PublicKey;

interface KeyProvider<U extends PublicKey, R extends PrivateKey> {

    U getPublicKeyById(String keyId);

    R getPrivateKey();

    String getPrivateKeyId();
}
