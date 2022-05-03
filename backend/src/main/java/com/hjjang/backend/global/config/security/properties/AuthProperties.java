package com.hjjang.backend.global.config.security.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties("auth-properties")
public class AuthProperties {
    private TokenProperties tokenProperties = new TokenProperties();
    private JwtProperties jwtProperties = new JwtProperties();
    private CorsProperties corsProperties = new CorsProperties();
    private OAuth2Properties oAuth2Properties = new OAuth2Properties();
    @Data
    public static class TokenProperties {
        @Value("auth-properties.token-properties.token-secret-key")
        private String tokenSecretKey;
        private long tokenExpireDate;
        private long refreshTokenExpiry;
    }
    @Data
    public static class JwtProperties {
        @Value("auth-properties.jwt-properties.secret-key")
        private String secretKey;
    }
    @Data
    public static class OAuth2Properties {
        @Value("auth-properties.o-auth2-properties.redirect-uris[]")
        private List<String> redirectUris = new ArrayList<>();
    }
    @Data
    public static class CorsProperties {
        @Value("auth-properties.cors-properties.allowed-origins")
        private String allowedOrigins;
        private String allowedMethods;
        private String allowedHeaders;
        private Long maxAge;
    }
}
