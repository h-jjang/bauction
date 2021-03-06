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
        //security ?????? user repository mock
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
    @DisplayName("?????? ????????? ?????? ?????? ??????")
    void getUserProfileSuccess() throws Exception {
        UserProfileDTO userProfileDTO = UserProfileDTO.builder()
                .userEmail("tester@tukorea.ac.kr")
                .userImageUrl("????????? url?????????")
                .userNickname("??? ???????????? ?????????")
                .userMannerTemperature(36L)
                .userUnivName("???????????????")
                .build();
        when(userProfileService.getUserProfile(any())).thenReturn(userProfileDTO);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/users/profile")
                        .header("Authorization", "Bearer USER_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserRestDocument.getProfile());
    }

    // ?????? ?????? ??????????????? ???????????? ???????????? ???????????? ???????????? ??? ??????
    @Test
    @DisplayName("?????? ????????? ?????? ?????? ????????? ????????? ?????? ?????? 401 ??????")
    void getUserProfileNoToken() throws Exception {

        when(userProfileService.getUserProfile(any())).thenThrow(new IllegalArgumentException("???????????? ?????? ???????????????."));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/users/profile")
                        .header("Authorization", "Bearer USER_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andDo(UserRestDocument.getProfile());
    }

    @Test
    @DisplayName("?????? ????????? ?????? ?????? ???????????? ?????? ?????? 403 ??????")
    void getUserProfileNoAuthorization() throws Exception {
        UserProfileDTO userProfileDTO = UserProfileDTO.builder()
                .userEmail("tester@tukorea.ac.kr")
                .userImageUrl("????????? url?????????")
                .userNickname("??? ???????????? ?????????")
                .userMannerTemperature(36L)
                .userUnivName("???????????????")
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