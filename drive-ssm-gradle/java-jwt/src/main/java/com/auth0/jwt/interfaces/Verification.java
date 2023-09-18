package com.auth0.jwt.interfaces;

import java.time.Instant;
import java.util.Date;
import java.util.function.BiPredicate;

public interface Verification {

    default Verification withIssuer(String issuer) {
        return withIssuer(new String[]{issuer});
    }

    Verification withIssuer(String... issuer);

    Verification withSubject(String subject);

    Verification withAudience(String... audience);

    Verification withAnyOfAudience(String... audience);

    Verification acceptLeeway(long leeway) throws IllegalArgumentException;

    Verification acceptExpiresAt(long leeway) throws IllegalArgumentException;

    Verification acceptNotBefore(long leeway) throws IllegalArgumentException;

    Verification acceptIssuedAt(long leeway) throws IllegalArgumentException;

    Verification withJWTId(String jwtId);

    Verification withClaimPresence(String name) throws IllegalArgumentException;

    Verification withNullClaim(String name) throws IllegalArgumentException;

    Verification withClaim(String name, Boolean value) throws IllegalArgumentException;

    Verification withClaim(String name, Integer value) throws IllegalArgumentException;

    Verification withClaim(String name, Long value) throws IllegalArgumentException;

    Verification withClaim(String name, Double value) throws IllegalArgumentException;

    Verification withClaim(String name, String value) throws IllegalArgumentException;

    Verification withClaim(String name, Date value) throws IllegalArgumentException;

    default Verification withClaim(String name, Instant value) throws IllegalArgumentException {
        return withClaim(name, value != null ? Date.from(value) : null);
    }

    Verification withClaim(String name, BiPredicate<Claim, DecodedJWT> predicate) throws IllegalArgumentException;

    Verification withArrayClaim(String name, String... items) throws IllegalArgumentException;

    Verification withArrayClaim(String name, Integer... items) throws IllegalArgumentException;

    Verification withArrayClaim(String name, Long ... items) throws IllegalArgumentException;

    Verification ignoreIssuedAt();

    JWTVerifier build();
}
