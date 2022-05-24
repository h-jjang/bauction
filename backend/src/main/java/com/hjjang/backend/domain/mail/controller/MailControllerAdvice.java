package com.hjjang.backend.domain.mail.controller;

import static org.springframework.http.HttpStatus.*;

import com.hjjang.backend.domain.mail.exception.ExceptionMessage;
import com.hjjang.backend.domain.mail.exception.MailException;
import com.hjjang.backend.domain.mail.exception.TimeLimitException;
import com.hjjang.backend.domain.mail.exception.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MailControllerAdvice {

	@ExceptionHandler(value = MailException.class)
	public ResponseEntity<String> mailExceptionHandle() {
		return ResponseEntity.status(BAD_REQUEST).body(ExceptionMessage.INVALID_MAIL.getMessage());
	}

	@ExceptionHandler(value = TimeLimitException.class)
	public ResponseEntity<String> timeLimitExceptionHandle() {
		return ResponseEntity.status(REQUEST_TIMEOUT).body(ExceptionMessage.TIME_LIMIT.getMessage());
	}

	@ExceptionHandler(value = UnauthorizedException.class)
	public ResponseEntity<String> unauthorizedExceptionHandle() {
		return ResponseEntity.status(UNAUTHORIZED).body(ExceptionMessage.INVALID_CODE.getMessage());
	}
}
