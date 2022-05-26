package com.hjjang.backend.domain.chat.exception;

import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;

public class NotFoundChatRoomEntranceEntityException extends BusinessException {

    public NotFoundChatRoomEntranceEntityException() {
        super(ErrorCode.CHATROOM_ENTRANCE_NOT_FOUND);
    }

}
