package com.hjjang.backend.global.response.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    //Mail
    MAIL_SEND_SUCCESS("M001","코드 생성하여 메일 전송."),
    MAIL_CHECK_SUCCESS("M002", "메일 인증 확인."),

    //User
    USER_PROFILE_SUCCESS( "U001", "프로필 조회 완료."),;

    private final String code;
    private final String message;
}
