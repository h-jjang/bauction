package com.hjjang.backend.domain.user.controller;

import com.hjjang.backend.domain.user.dto.LoginRequest;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.UserRefreshToken;
import com.hjjang.backend.domain.user.repository.UserRefreshTokenRepository;
import com.hjjang.backend.domain.user.service.UserAuthService;
import com.hjjang.backend.global.config.properties.AuthProperties;
import com.hjjang.backend.global.config.security.token.AuthToken;
import com.hjjang.backend.global.config.security.token.AuthTokenProvider;
import com.hjjang.backend.global.dto.ApiResponse;
import com.hjjang.backend.global.util.CookieUtil;
import com.hjjang.backend.global.util.HeaderUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final AuthProperties authProperties;
    private final AuthTokenProvider tokenProvider;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final UserAuthService userAuthService;

    private final static long THREE_DAYS_MSEC = 259200000;
    private final static String REFRESH_TOKEN = "refresh_token";

    @PostMapping("/login")
    public ApiResponse login(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody LoginRequest loginRequest
    ) {
        String token = userAuthService.login(request, response, loginRequest);
        return ApiResponse.success("token", token);
    }

    @GetMapping("/refresh")
    public ApiResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
        // access token 확인
        String accessToken = HeaderUtil.getAccessToken(request);
        AuthToken authToken = tokenProvider.convertAuthToken(accessToken);
        if (!authToken.validate()) {
            return ApiResponse.invalidAccessToken();
        }

        // 만료된 access token 인지 확인
        Claims claims = authToken.getExpiredTokenClaims();
        if (claims == null) {
            return ApiResponse.notExpiredTokenYet();
        }

        String userId = claims.getSubject();
        RoleType roleType = RoleType.of(claims.get("role", String.class));

        // refresh token
        String refreshToken = CookieUtil.getCookie(request, REFRESH_TOKEN)
                .map(Cookie::getValue)
                .orElse((null));
        AuthToken authRefreshToken = tokenProvider.convertAuthToken(refreshToken);

        if (authRefreshToken.validate()) {
            return ApiResponse.invalidRefreshToken();
        }

        // userId, refresh token 으로 DB 확인
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserIdAndRefreshToken(userId, refreshToken);
        if (userRefreshToken == null) {
            return ApiResponse.invalidRefreshToken();
        }

        Date now = new Date();
        AuthToken newAccessToken = tokenProvider.createAuthToken(
                userId, roleType.getCode(), new Date(now.getTime() + authProperties.getTokenProperties().getTokenExpireDate())
        );
        userAuthService.reissueRefreshTokenIfValidTimeleft3days(request, response, userRefreshToken, authRefreshToken, now);

        return ApiResponse.success("token", newAccessToken.getToken());
    }
}
