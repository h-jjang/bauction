package com.hjjang.backend.security;

import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.global.security.principal.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User givenUser = User.builder()
                .email(customUser.email())
                .imageUrl(customUser.imageUrl())
                .isPushAgree(customUser.isPushAgree())
                .mannerTemperature(customUser.mannerTemperature())
                .nickName(customUser.nickName())
                .providerId(customUser.providerId())
                .role(customUser.roles())
                .univId(customUser.univId())
                .build();
            
        UserPrincipal principal = UserPrincipal.create(givenUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}