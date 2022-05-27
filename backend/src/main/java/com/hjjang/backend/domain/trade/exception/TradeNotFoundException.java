package com.hjjang.backend.domain.trade.exception;

import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;
import com.hjjang.backend.global.response.response.ErrorResponse;

import java.util.List;

public class TradeNotFoundException extends BusinessException {
    public TradeNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public TradeNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TradeNotFoundException(ErrorCode errorCode, List<ErrorResponse.FieldError> errors) {
        super(errorCode, errors);
    }
}
