package com.hjjang.backend.security;

import com.hjjang.backend.domain.user.entity.Agreement;
import com.hjjang.backend.domain.user.entity.RoleType;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

    long id() default 1L;

    String email() default "kevinkim@email.com";

    String imageUrl() default "이미지입니다아아아";

    String nickName() default "김겨여여연";

    RoleType roles() default RoleType.USER;

    String providerId() default "kakao123456";

    long mannerTemperature() default 36L;

    Agreement isPushAgree() default Agreement.AGREE;

    long univId() default 1L;
}