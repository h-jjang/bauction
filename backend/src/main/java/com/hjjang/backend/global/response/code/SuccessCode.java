package com.hjjang.backend.global.response.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    //User
    USER_PROFILE_SUCCESS( "U001", "프로필 조회 완료."),
    CREATE_CHAT_ROOM_SUCCESS( "CR001", "채팅방 생성 완료.");

    private final String code;
    private final String message;
}
