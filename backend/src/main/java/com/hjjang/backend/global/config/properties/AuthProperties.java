package com.hjjang.backend.global.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {

    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();
    private final Cors corsProperties = new Cors();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Auth {
        private String tokenSecretKey;
        private long tokenExpireDate;
        private long refreshTokenExpiry;
    }

    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Cors {
        private String allowedOrigins;
        private String allowedMethods;
        private String allowedHeaders;
        private Long maxAge;
    }
}
