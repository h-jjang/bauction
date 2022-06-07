package com.hjjang.backend.global.response.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //Global
    INTERNAL_SERVER_ERROR("G001", "내부 서버 오류"),
    NOT_ALLOWED_METHOD("G002", "허용 되지 않은 HTTP method"),
    INVALID_INPUT_VALUE("G003", "검증 되지 않은 입력"),

    //User
    USER_NOT_FOUND("U001", "존재 하지 않는 사용자입니다."),
    NO_AUTHORITY("U002", "권한이 없음"),

    // chatRoom
    SELLER_NOT_FOUND("CR001", "존재 하지 않는 판매자"),
    BUYER_NOT_FOUND("CR002", "존재 하지 않는 구매자"),
    CANNOT_CHAT_BY_SELF("CR003", "자기 자신과는 채팅을 할 수 없다."),
    CHATROOM_NOT_FOUND("CR004", "존재하지 않는 채팅방입니다."),
    CHATROOM_IS_ALREADY_HIDDEN("CR005", "이미 숨김 처리된 채팅방입니다."),
    CHATROOM_ENTRANCE_NOT_FOUND("CR006", "참여중인 채팅방이 없습니다."),
    CHATROOM_USER_NOT_FOUND("CR007", "참여하고 있는 채팅방이 채팅방입니다."),;

    private final String code;
    private final String message;
}
