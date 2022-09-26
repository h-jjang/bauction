package com.hjjang.backend.domain.chat.exception;

import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;

public class IsAlreadyHiddenChatRoomException extends BusinessException {

    public IsAlreadyHiddenChatRoomException() {
        super(ErrorCode.CHATROOM_IS_ALREADY_HIDDEN);
    }

}
