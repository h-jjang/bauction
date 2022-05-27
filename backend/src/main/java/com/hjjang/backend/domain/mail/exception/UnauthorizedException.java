package com.hjjang.backend.domain.mail.exception;

import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;

public class UnauthorizedException extends BusinessException {

	public UnauthorizedException() {
		super(ErrorCode.INVALID_CODE);
	}
}
