package com.auth0.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static final String SECRET = "$2a$10$WRnB6tBL4w1sG4cgWHH25OANRZ6IMt1f5M47Z6vDkimeUsQwJvtyS"; // jwt 票据
    public static final String ISSUER = "wxpaperplan"; // jwt 签发者
    public static final String AUDIENCE = "admin-token"; // jwt 为谁签发 群体
    public static final long EXPIRE_LOGIN = 50 * 60 * 1000L; // 50分钟 登录过期时间

    public static String createJWT(Integer userId){
        char[] alphabet = "0123456789".toCharArray();
        String nanoidId = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, alphabet, 8);

        long nowMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 10, 10);

        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put(HeaderParams.ALGORITHM, "HS256");
        headerClaims.put(HeaderParams.TYPE, "JWT");

        Map<String, Object> payloadClaims = new HashMap<>();
        payloadClaims.put(RegisteredClaims.ISSUER, ISSUER);
        payloadClaims.put(RegisteredClaims.SUBJECT, userId);
        payloadClaims.put(RegisteredClaims.AUDIENCE, AUDIENCE);
        payloadClaims.put(RegisteredClaims.ISSUED_AT, new Date(nowMillis));
        payloadClaims.put(RegisteredClaims.EXPIRES_AT, new Date(nowMillis + EXPIRE_LOGIN));
        payloadClaims.put(RegisteredClaims.NOT_BEFORE, calendar.getTime());
        payloadClaims.put(RegisteredClaims.JWT_ID, Integer.parseInt(nanoidId));

        JWTCreator jwtCreator = new JWTCreator(Algorithm.HMAC256(SECRET), headerClaims, payloadClaims);
        return jwtCreator.sign();
    }

    public static Integer verifyJWT(String token) {
        JWTVerifier verifier = (JWTVerifier) JWTVerifier.init(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        Claim claim = claims.get(RegisteredClaims.SUBJECT);
        return claim.asInt();
    }
}
