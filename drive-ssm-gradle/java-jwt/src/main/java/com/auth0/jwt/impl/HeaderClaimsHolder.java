package com.auth0.jwt.impl;

import java.util.Map;

public final class HeaderClaimsHolder extends ClaimsHolder {
    public HeaderClaimsHolder(Map<String, Object> claims) {
        super(claims);
    }
}
