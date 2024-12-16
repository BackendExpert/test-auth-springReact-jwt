package com.jehan.server.service;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

@Component
public class JWTUtils {
    private SecretKey Key;

    private static final long EXPIRATION_TIME = 86400000;

    public JWTUtils(){
        String secreteString = "54654665798464874984684984694846487466846546ABCD6545465465466ABCD";
        byte[] keyBuytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
        this.Key = new SecretKeySpec(keyBuytes, "HmacSHA256");
    }

    public String genarateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }

    public String genarateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails){
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }



}
