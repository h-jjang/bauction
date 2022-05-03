package com.hjjang.backend.global.security;

import com.hjjang.backend.global.config.security.properties.AuthProperties;
import com.hjjang.backend.global.security.token.AuthTokenProvider;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthProviderConfig {

    private final AuthProperties authProperties;

    @Bean
    public AuthTokenProvider jwtProvider() {
        return new AuthTokenProvider(authProperties.getJwtProperties().getSecretKey());
    }

}
