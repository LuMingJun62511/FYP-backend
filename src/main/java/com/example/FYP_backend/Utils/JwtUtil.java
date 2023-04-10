package com.example.FYP_backend.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Date;

public final class JwtUtil {

    private final static String secretKey = "whatever";//Secret key

    private final static Duration expiration = Duration.ofHours(2);//The expiration time is temporarily set to 2 days

    public static String generate(String userName) {
        Date expiryDate = new Date(System.currentTimeMillis() + expiration.toMillis());//expiration time
        return Jwts.builder()
                .setSubject(userName) // Put userName in JWT
                .setIssuedAt(new Date()) // Set JWT issuance time
                .setExpiration(expiryDate)  // set expiration time
                .signWith(SignatureAlgorithm.HS512, secretKey) // Set encryption algorithm and secret key
                .compact();
    }

    /**
     Parsing JWT Parsing successfully returns the Claims object, parsing fails Claims is null
     */
    public static Claims parse(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        Claims claims = null;
        // token expiration or illegal token will cause parsing failure
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            System.err.println("err reason: "+e);
        }
        return claims;
    }
}







//文章原文见 https://juejin.cn/post/6866978103776772109#heading-9
