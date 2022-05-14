package com.hjjang.backend.global.security;

import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.global.config.security.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collections;
import java.util.Date;


@RequiredArgsConstructor
public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    private final AuthProperties authProperties;

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        Date now = new Date();

        // 가짜 user 정보 설정
        User principal = new User(
                customUser.providerId(),
                "",
                Collections.singletonList(new SimpleGrantedAuthority(RoleType.USER.getCode()))
        );

        // 가짜 security 인증 user 저장
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                "",
                principal.getAuthorities()
        );
        securityContext.setAuthentication(authentication);
        return securityContext;
    }
}