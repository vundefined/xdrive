package com.auth0.jwt;

public final class RegisteredClaims {

    private RegisteredClaims() {
    }

    /**
    * 签发者
    * */
    public static final String ISSUER = "iss";

    /**
     * 主体
     * */
    public static final String SUBJECT = "sub";

    /**
     * 为谁签发 群体
     * */
    public static final String AUDIENCE = "aud";

    /**
     * 过期时间
     * */
    public static final String EXPIRES_AT = "exp";

    /**
     * 在某时间之前不可用
     * */
    public static final String NOT_BEFORE = "nbf";

    /**
     * (Issued At) 签发时间
     * */
    public static final String ISSUED_AT = "iat";

    /**
     * 编号
     * */
    public static final String JWT_ID = "jti";

}
