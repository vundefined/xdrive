package com.auth0.jwt.impl;

import java.util.Map;

public final class PayloadClaimsHolder extends ClaimsHolder {
    public PayloadClaimsHolder(Map<String, Object> claims) {
        super(claims);
    }
}
