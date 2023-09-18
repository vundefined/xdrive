package com.auth0.jwt.interfaces;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public interface ECDSAKeyProvider extends KeyProvider<ECPublicKey, ECPrivateKey> {
}
