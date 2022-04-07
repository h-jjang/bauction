package com.hjjang.backend.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {

    private final TokenProperties tokenProperties = new TokenProperties();
    private final OAuth2Properties oAuth2Properties = new OAuth2Properties();
    private final CorsProperties corsProperties = new CorsProperties();
    private final JwtProperties jwtProperties = new JwtProperties();

    @Getter
    @Setter
    public final static class TokenProperties {
        private String tokenSecretKey;
        private long tokenExpireDate;
        private long refreshTokenExpiry;
    }

    @Getter
    @Setter
    public final static class JwtProperties {
        private String secretKey;
    }

    @Getter
    @Setter
    public final static class OAuth2Properties {
        private List<String> redirectUris = new ArrayList<>();
    }

    @Getter
    @Setter
    public final static class CorsProperties {
        private String allowedOrigins;
        private String allowedMethods;
        private String allowedHeaders;
        private Long maxAge;
    }
}
