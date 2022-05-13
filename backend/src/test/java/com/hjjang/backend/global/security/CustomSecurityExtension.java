package com.hjjang.backend.global.security;

import com.hjjang.backend.domain.user.entity.Agreement;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.repository.UserRefreshTokenRepository;
import com.hjjang.backend.domain.user.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.restdocs.RestDocumentationExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ComponentScan(basePackages = "com.hjjang.backend.global.config.security")
@ComponentScan(basePackages = "com.hjjang.backend.global.security")
@ExtendWith(RestDocumentationExtension.class)
public class CustomSecurityExtension {

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected UserRefreshTokenRepository userRefreshTokenRepository;

    public void userInfoSetUp() {
        User givenUser = User.builder()
                .email("kevinkim@email.com")
                .imageUrl("이미지입니다아아아")
                .isPushAgree(Agreement.AGREE)
                .mannerTemperature(36L)
                .nickName("김겨여여연")
                .providerId("kakao123456")
                .role(RoleType.USER)
                .univId(1L)
                .build();
        when(userRepository.findUserByProviderId(any())).thenReturn(Optional.of(givenUser));
    }


}
