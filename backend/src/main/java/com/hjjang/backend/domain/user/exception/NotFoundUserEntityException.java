package com.hjjang.backend.domain.user.exception;

import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;

public class NotFoundUserEntityException extends BusinessException {
    public NotFoundUserEntityException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
