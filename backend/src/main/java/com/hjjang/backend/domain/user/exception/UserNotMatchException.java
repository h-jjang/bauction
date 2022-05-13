package com.hjjang.backend.domain.user.exception;

import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;
import com.hjjang.backend.global.response.response.ErrorResponse;

import java.util.List;

public class UserNotMatchException extends BusinessException {
    public UserNotMatchException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public UserNotMatchException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserNotMatchException(ErrorCode errorCode, List<ErrorResponse.FieldError> errors) {
        super(errorCode, errors);
    }
}
