package com.auth0.jwt.interfaces;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public interface RSAKeyProvider extends KeyProvider<RSAPublicKey, RSAPrivateKey> {
}
