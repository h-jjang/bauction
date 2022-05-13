package com.hjjang.backend.domain.user.service;

import com.hjjang.backend.domain.user.dto.UserProfileDTO;
import com.hjjang.backend.domain.user.entity.Agreement;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.repository.UserRepository;
import com.hjjang.backend.global.security.WithMockCustomUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceTest {

    @InjectMocks
    private UserProfileService userProfileService;

    @Mock
    private UserRepository userRepository;

    @Test
    @WithMockCustomUser
    @DisplayName("유저 조회 api test")
    void getUserProfileTest() {
        // given
        User givenUser = User.builder()
                .email("tester@tukorea.ac.kr")
                .imageUrl("이미지 url")
                .isPushAgree(Agreement.AGREE)
                .mannerTemperature(36L)
                .nickName("kevinkim")
                .providerId("123412512")
                .role(RoleType.USER)
                .univId(null)
                .build();

        UserProfileDTO exceptUserProfileDTO = UserProfileDTO.builder()
                .userEmail("tester@tukorea.ac.kr")
                .userImageUrl("이미지 url")
                .userMannerTemperature(36L)
                .userNickname("kevinkim")
                .userUnivName("산기대")
                .build();

        // when
        when(userRepository.findUserByProviderId(any())).thenReturn(Optional.of(givenUser));
        UserProfileDTO actualUserProfileDTO = userProfileService.getUserProfile(givenUser.getProviderId());

        // then
        assertAll(
                () -> assertEquals(exceptUserProfileDTO.getUserEmail(), actualUserProfileDTO.getUserEmail()),
                () -> assertEquals(exceptUserProfileDTO.getUserNickname(), actualUserProfileDTO.getUserNickname()),
                () -> assertEquals(exceptUserProfileDTO.getUserImageUrl(), actualUserProfileDTO.getUserImageUrl()),
                () -> assertEquals(exceptUserProfileDTO.getUserMannerTemperature(), actualUserProfileDTO.getUserMannerTemperature()),
                () -> assertEquals(exceptUserProfileDTO.getUserUnivName(), actualUserProfileDTO.getUserUnivName())
        );

    }

}