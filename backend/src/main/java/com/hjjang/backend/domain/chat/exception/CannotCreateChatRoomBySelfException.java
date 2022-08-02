package com.hjjang.backend.domain.chat.exception;

import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;

public class CannotCreateChatRoomBySelfException extends BusinessException {

    public CannotCreateChatRoomBySelfException() {
        super(ErrorCode.CANNOT_CHAT_BY_SELF);
    }

}
