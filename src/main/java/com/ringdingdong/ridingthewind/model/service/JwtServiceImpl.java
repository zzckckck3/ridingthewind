package com.ringdingdong.ridingthewind.model.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService{

    public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    //	SALT는 토큰 유효성 확인 시 사용하기 때문에 외부에 노출되지 않게 주의해야 한다.
    private static final String SALT = "dragon";

    private static final int ACCESS_TOKEN_EXPIRE_MINUTES = 1; // 분단위
    private static final int REFRESH_TOKEN_EXPIRE_MINUTES = 2; // 주단위


    @Override
    public <T> String createAccessToken(String key, T data) {
        return create(key, data, "access-token", 1000 * 60 * ACCESS_TOKEN_EXPIRE_MINUTES);
    }

    @Override
    public <T> String createRefreshToken(String key, T data) {
        return create(key, data, "refresh-token", 1000 * 60 * 60 * 24 * 7 * REFRESH_TOKEN_EXPIRE_MINUTES);
    }


    @Override
    public <T> String create(String key, T data, String subject, long expire) {
        // Payload 설정 : 생성일(IssuedAt), 유효기간(Expiration),
            // 토큰 제목 (SUbject), 데이터(Claim) 등 정보 세팅.
        Claims claims = Jwts.claims()
                .setSubject(subject) // 토큰 제목 설정
                .setIssuedAt(new Date()) // 토큰 생성일 설정
                .setExpiration(new Date(System.currentTimeMillis() + expire)); // 만료일 설정(유효기간)


        claims.put(key, data);

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();

        return jwt;
    }

    private byte[] generateKey(){
        byte[] key = null;
        try {
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            if(logger.isInfoEnabled()) {
                e.printStackTrace();
            } else {
                logger.error("Making JWT Key Error ::: {} ", e.getMessage());
            }
        }
        return key;
    }
}
