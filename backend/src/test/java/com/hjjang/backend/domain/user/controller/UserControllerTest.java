package com.hjjang.backend.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjjang.backend.domain.user.controller.docs.UserRestDocument;
import com.hjjang.backend.domain.user.dto.UserProfileDTO;
import com.hjjang.backend.domain.user.service.UserProfileService;
import com.hjjang.backend.global.security.CustomSecurityExtension;
import com.hjjang.backend.global.security.WithMockCustomUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
class UserControllerTest extends CustomSecurityExtension {

    @MockBean
    private UserProfileService userProfileService;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentationContextProvider) {
        //security 관련 user repository mock
        super.userInfoSetUp();
        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .apply(springSecurity())
                .build();

    }

    @Test
    @WithMockCustomUser
    @DisplayName("유저 프로필 정보 조회 성공")
    void getUserProfileSuccess() throws Exception {
        UserProfileDTO userProfileDTO = UserProfileDTO.builder()
                .userEmail("tester@tukorea.ac.kr")
                .userImageUrl("이미지 url입니다")
                .userNickname("내 닉네임은 테스타")
                .userMannerTemperature(36L)
                .userUnivName("한국공학대")
                .build();
        when(userProfileService.getUserProfile(any())).thenReturn(userProfileDTO);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/users/profile")
                        .header("Authorization", "Bearer USER_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserRestDocument.getProfile());
    }

    // 아래 예외 테스트들은 정상적인 테스트가 아니므로 실패되는 게 맞음
    @Test
    @DisplayName("유저 프로필 정보 조회 토큰이 없어서 인증 실패 401 에러")
    void getUserProfileNoToken() throws Exception {

        when(userProfileService.getUserProfile(any())).thenThrow(new IllegalArgumentException("유효하지 않은 토큰입니다."));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/users/profile")
                        .header("Authorization", "Bearer USER_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andDo(UserRestDocument.getProfile());
    }

    @Test
    @DisplayName("유저 프로필 정보 조회 유효하지 않은 권한 403 에러")
    void getUserProfileNoAuthorization() throws Exception {
        UserProfileDTO userProfileDTO = UserProfileDTO.builder()
                .userEmail("tester@tukorea.ac.kr")
                .userImageUrl("이미지 url입니다")
                .userNickname("내 닉네임은 테스타")
                .userMannerTemperature(36L)
                .userUnivName("한국공학대")
                .build();
        when(userProfileService.getUserProfile(any())).thenReturn(userProfileDTO);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/users/profile")
                        .header("Authorization", "Bearer USER_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andDo(UserRestDocument.getProfile());
    }

}