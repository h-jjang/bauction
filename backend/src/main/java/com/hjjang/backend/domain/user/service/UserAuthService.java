package com.hjjang.backend.domain.user.service;

import com.hjjang.backend.domain.user.dto.LoginRequest;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.UserRefreshToken;
import com.hjjang.backend.domain.user.repository.UserRefreshTokenRepository;
import com.hjjang.backend.global.config.properties.AuthProperties;
import com.hjjang.backend.global.config.security.principal.UserPrincipal;
import com.hjjang.backend.global.config.security.token.AuthToken;
import com.hjjang.backend.global.config.security.token.AuthTokenProvider;
import com.hjjang.backend.global.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.hjjang.backend.global.config.security.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.REFRESH_TOKEN;

@RequiredArgsConstructor
@Service
public class UserAuthService {

    private final AuthProperties authProperties;
    private final AuthTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final static long THREE_DAYS_MSEC = 259200000;

    public String login(HttpServletRequest request, HttpServletResponse response,
                        LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getId(),
                        loginRequest.getPassword()
                )
        );

        String userId = loginRequest.getId();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Date now = new Date();
        AuthToken accessToken = tokenProvider.createAuthToken(
                userId,
                ((UserPrincipal) authentication.getPrincipal()).getRoleType().getCode(),
                new Date(now.getTime() + authProperties.getTokenProperties().getTokenExpireDate())
        );

        long refreshTokenExpiry = authProperties.getTokenProperties().getRefreshTokenExpiry();
        AuthToken refreshToken = tokenProvider.createAuthToken(
                authProperties.getTokenProperties().getTokenSecretKey(),
                new Date(now.getTime() + refreshTokenExpiry)
        );

        // userId refresh token 으로 DB 확인
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserId(userId);
        if (userRefreshToken == null) {
            // 없는 경우 새로 등록
            userRefreshToken = new UserRefreshToken(userId, refreshToken.getToken());
            userRefreshTokenRepository.saveAndFlush(userRefreshToken);
        } else {
            // DB에 refresh 토큰 업데이트
            userRefreshToken.setRefreshToken(refreshToken.getToken());
        }

        int cookieMaxAge = (int) refreshTokenExpiry / 60;
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtil.addCookie(response, REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);

        return accessToken.getToken();
    }

    public AuthToken createAuthToken(String userId, RoleType roleType, Date expiredDate) {
        Date now = new Date();
        return tokenProvider.createAuthToken(
                userId, roleType.getCode(),
                new Date(now.getTime() + authProperties.getTokenProperties().getTokenExpireDate())
        );
    }


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
