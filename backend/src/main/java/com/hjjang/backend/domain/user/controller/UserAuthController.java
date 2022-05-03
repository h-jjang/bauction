package com.hjjang.backend.domain.user.controller;

import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.UserRefreshToken;
import com.hjjang.backend.domain.user.repository.UserRefreshTokenRepository;
import com.hjjang.backend.domain.user.service.UserAuthService;
import com.hjjang.backend.global.config.security.properties.AuthProperties;
import com.hjjang.backend.global.security.token.AuthToken;
import com.hjjang.backend.global.security.token.AuthTokenProvider;
import com.hjjang.backend.global.dto.ApiResponse;
import com.hjjang.backend.global.util.CookieUtil;
import com.hjjang.backend.global.util.HeaderUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private static final long THREE_DAYS_MSEC = 259200000;
    private static final String REFRESH_TOKEN = "refresh_token";

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
        String providerId = claims.getSubject();
        RoleType roleType = RoleType.of(claims.get("role", String.class));

        // refresh token
        String refreshToken = CookieUtil.getCookie(request, REFRESH_TOKEN)
            .map(Cookie::getValue)
            .orElse((null));
        AuthToken authRefreshToken = tokenProvider.convertAuthToken(refreshToken);

        if (authRefreshToken.validate()) {
            return ApiResponse.invalidRefreshToken();
        }

        // providerId, refresh token 으로 DB 확인
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByProviderIdAndRefreshToken(providerId,
            refreshToken);
        if (userRefreshToken == null) {
            return ApiResponse.invalidRefreshToken();
        }

        Date now = new Date();
        AuthToken newAccessToken = tokenProvider.createAuthToken(
            providerId, roleType.getCode(),
            new Date(now.getTime() + authProperties.getTokenProperties().getTokenExpireDate())
        );
        userAuthService.reissueRefreshTokenIfValidTimeleft3days(request, response, userRefreshToken, authRefreshToken,
            now);

        return ApiResponse.success("token", newAccessToken.getToken());
    }
}
