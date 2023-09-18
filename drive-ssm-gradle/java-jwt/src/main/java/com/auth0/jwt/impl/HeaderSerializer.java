package com.auth0.jwt.impl;

public class HeaderSerializer extends ClaimsSerializer<HeaderClaimsHolder> {
    public HeaderSerializer() {
        super(HeaderClaimsHolder.class);
    }
}
