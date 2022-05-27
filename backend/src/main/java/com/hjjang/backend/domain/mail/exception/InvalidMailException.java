package com.hjjang.backend.domain.mail.exception;

import com.hjjang.backend.global.execption.BusinessException;
import com.hjjang.backend.global.response.code.ErrorCode;

public class InvalidMailException extends BusinessException {

	public InvalidMailException() {
		super(ErrorCode.INVALID_MAIL);
	}
}
