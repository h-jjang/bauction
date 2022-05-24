package com.hjjang.backend.domain.mail.exception;

import static com.hjjang.backend.domain.mail.exception.TimeLimitException.*;

import com.hjjang.backend.domain.mail.dto.MailRequest;

public class UnauthorizedException extends RuntimeException {

	private UnauthorizedException() { }

	public static void checkValidCode(MailRequest mailRequest, String code) {
		checkTimeLimit(code);
		if (!mailRequest.getCode().equals(code)) {
			throw new UnauthorizedException();
		}
	}
}
