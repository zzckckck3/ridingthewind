package com.ringdingdong.ridingthewind.model.service;

public interface JwtService {
    <T> String createAccessToken(String key, T data);

    <T> String createRefreshToken(String key, T data);

    <T> String create(String key, T data, String subject, long expir);
}
