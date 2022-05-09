package com.hjjang.backend.domain.user.controller.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class UserRestDocument {
    public static RestDocumentationResultHandler getProfile() {
        return document("v1/users/profile",
                requestHeaders(
                        headerWithName("authorization").description("Bearer 토큰")),
                responseFields(
                        fieldWithPath("status").type(JsonFieldType.NUMBER).description("status code"),
                        fieldWithPath("code").type(JsonFieldType.STRING).description("Business code"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("response message"),
                        fieldWithPath("data.userNickname").type(JsonFieldType.STRING).description("user 닉네임"),
                        fieldWithPath("data.userImageUrl").type(JsonFieldType.STRING).description("user 프로필 사진"),
                        fieldWithPath("data.userEmail").type(JsonFieldType.STRING).description("user 이메일"),
                        fieldWithPath("data.userMannerTemperature").type(JsonFieldType.NUMBER).description("user 매너 온도"),
                        fieldWithPath("data.userUnivName").type(JsonFieldType.STRING).description("user 대학 이름")
                )
        );
    }
}
