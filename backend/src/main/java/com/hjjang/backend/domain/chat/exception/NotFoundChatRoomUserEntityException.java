package com.hjjang.backend.domain.chat.exception;

import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;

public class NotFoundChatRoomUserEntityException extends BusinessException {

    public NotFoundChatRoomUserEntityException() {
        super(ErrorCode.CHATROOM_USER_NOT_FOUND);
    }

}
