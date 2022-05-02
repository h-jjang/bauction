package com.hjjang.backend.global.response.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    //User
    USERPROFILE_SUCCESS(200, "U001", "프로필 조회 완료."),;

    private final int status;
    private final String code;
    private final String message;
}
