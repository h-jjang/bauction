package com.hjjang.backend.global.response.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    //Mail
    MAIL_SEND_SUCCESS("M001", "코드 생성하여 메일 전송."),
    MAIL_CHECK_SUCCESS("M002", "메일 인증 확인."),

    //User
    USER_PROFILE_SUCCESS("U001", "프로필 조회 완료."),

    //Trade
    TRADE_CREATE_SUCCESS("T001", "거래기록 생성 완료"),
    TRADE_FIND_SUCCESS("T002", "거래기록 조회 완료"),
    TRADE_UPDATE_SUCCESS("T003", "거래기록 갱신 완료"),
    TRADE_DELETE_SUCCESS("T004", "거래기록 삭제 완료"),

    //chat
    CREATE_CHAT_ROOM_SUCCESS("CR001", "채팅방 생성 완료."),

    //Post
    POST_CREATE_SUCCESS("P001", "게시글 등록 완료.");

    private final String code;
    private final String message;
}
