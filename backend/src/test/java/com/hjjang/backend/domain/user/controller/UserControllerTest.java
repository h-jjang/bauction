package com.hjjang.backend.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjjang.backend.domain.user.controller.docs.UserRestDocument;
import com.hjjang.backend.domain.user.dto.UserProfileDTO;
import com.hjjang.backend.domain.user.repository.UserRefreshTokenRepository;
import com.hjjang.backend.domain.user.repository.UserRepository;
import com.hjjang.backend.domain.user.service.UserProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan(basePackages = "com.hjjang.backend.global.config.security")
@ComponentScan(basePackages = "com.hjjang.backend.global.security")
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected UserRefreshTokenRepository userRefreshTokenRepository;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserProfileService userProfileService;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentationContextProvider) {
        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .apply(springSecurity())
                .build();

    }

    @Test
    @DisplayName("유저 프로필 정보 조회")
    void get_user_profile_success() throws Exception {
        UserProfileDTO userProfileDTO = UserProfileDTO.builder()
                .userEmail("tester@tukorea.ac.kr")
                .userImageUrl("이미지 url입니다")
                .userNickname("내 닉네임은 테스타")
                .userMannerTemperature(36L)
                .userUnivName("한국공학대")
                .build();
        when(userProfileService.getUserProfile()).thenReturn(userProfileDTO);

        mockMvc.perform(get("/api/v1/users/profile")
                        .header("authorization", "Bearer USER_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserRestDocument.getProfile());
    }

}