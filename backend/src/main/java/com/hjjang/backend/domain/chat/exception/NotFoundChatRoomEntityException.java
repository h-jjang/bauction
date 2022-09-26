package com.hjjang.backend.domain.chat.exception;

import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;

public class NotFoundChatRoomEntityException extends BusinessException {

    public NotFoundChatRoomEntityException() {
        super(ErrorCode.CHATROOM_NOT_FOUND);
    }

}
