package com.hjjang.backend.global.response.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //Global
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "G001", "내부 서버 오류"),
    NOT_ALLOWED_METHOD(HttpStatus.METHOD_NOT_ALLOWED.value(), "G002", "허용 되지 않은 HTTP method"),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "G003", "검증 되지 않은 입력"),

    //User
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "U001", "존재 하지 않는 사용자"),
    NO_AUTHORITY(HttpStatus.FORBIDDEN.value(), "U002", "권한이 없음"),;


    private final int httpStatus;
    private final String code;
    private final String message;
}
