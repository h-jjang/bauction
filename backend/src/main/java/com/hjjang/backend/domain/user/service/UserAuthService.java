package com.hjjang.backend.domain.user.service;

import static com.hjjang.backend.global.config.security.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.*;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.hjjang.backend.domain.user.entity.UserRefreshToken;
import com.hjjang.backend.domain.user.repository.UserRefreshTokenRepository;
import com.hjjang.backend.global.config.properties.AuthProperties;
import com.hjjang.backend.global.config.security.token.AuthToken;
import com.hjjang.backend.global.config.security.token.AuthTokenProvider;
import com.hjjang.backend.global.util.CookieUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserAuthService {

    private final AuthProperties authProperties;
    private final AuthTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final static long THREE_DAYS_MSEC = 259200000;

    public void reissueRefreshTokenIfValidTimeleft3days(HttpServletRequest request, HttpServletResponse response,
                                                        UserRefreshToken userRefreshToken, AuthToken authRefreshToken, Date now) {
        long validTime = authRefreshToken.getTokenClaims().getExpiration().getTime() - now.getTime();

        // refresh 토큰 기간이 3일 이하로 남은 경우, refresh 토큰 갱신
        if (validTime <= THREE_DAYS_MSEC) {
            // refresh 토큰 설정
            long refreshTokenExpiry = authProperties.getTokenProperties().getRefreshTokenExpiry();

            authRefreshToken = tokenProvider.createAuthToken(
                    authProperties.getTokenProperties().getTokenSecretKey(),
                    new Date(now.getTime() + refreshTokenExpiry)
            );

            // DB에 refresh 토큰 업데이트
            userRefreshToken.setRefreshToken(authRefreshToken.getToken());

            int cookieMaxAge = (int) refreshTokenExpiry / 60;
            CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
            CookieUtil.addCookie(response, REFRESH_TOKEN, authRefreshToken.getToken(), cookieMaxAge);
        }
    }

}
