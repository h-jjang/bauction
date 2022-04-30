package com.hjjang.backend.Integration;

import com.hjjang.backend.global.config.properties.AuthProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthPropertiesTest {

    @Autowired
    private AuthProperties authProperties;

    @Test
    void getAuth() {

        AuthProperties.TokenProperties tokenProperties = authProperties.getTokenProperties();
        assertAll(
                ()-> assertEquals("asodfhwoeihro1i34u1097r09u13rqfasc12", tokenProperties.getTokenSecretKey()),
                ()-> assertEquals(1800000, tokenProperties.getTokenExpireDate()),
                ()-> assertEquals(604800000, tokenProperties.getRefreshTokenExpiry())
        );
    }

    @Test
    void getJwt() {
        AuthProperties.JwtProperties jwtProperties = authProperties.getJwtProperties();
        assertAll(
                ()-> assertEquals("HJJangHandsomeGnuKenny120hy12049h014",jwtProperties.getSecretKey())
        );
    }

    @Test
    void getOauth() {
        AuthProperties.OAuth2Properties oAuthProperties = authProperties.getOAuth2Properties();
        assertAll(
                ()-> assertLinesMatch( List.of("http://localhost:3000/oauth/redirect"), oAuthProperties.getRedirectUris())
        );
    }

    @Test
    void cors() {
        AuthProperties.CorsProperties corsProperties = authProperties.getCorsProperties();
        assertAll(
                ()-> assertEquals("http://localhost:3000", corsProperties.getAllowedOrigins()),
                ()-> assertEquals("GET,POST,PUT,DELETE,OPTIONS", corsProperties.getAllowedMethods()),
                ()-> assertEquals("*", corsProperties.getAllowedHeaders()),
                ()-> assertEquals(3600, corsProperties.getMaxAge())
        );
    }


}