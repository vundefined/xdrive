package com.auth0.jwt.impl;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface ExpectedCheckHolder {

    String getClaimName();


    boolean verify(Claim claim, DecodedJWT decodedJWT);
}
