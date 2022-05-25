package com.hjjang.backend.domain.mail.exception.handler;

import static com.hjjang.backend.global.response.code.ErrorCode.*;
import static org.springframework.http.HttpStatus.*;

import com.hjjang.backend.domain.mail.exception.InvalidMailException;
import com.hjjang.backend.domain.mail.exception.UnauthorizedException;
import com.hjjang.backend.global.response.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MailExceptionHandler {

	@ExceptionHandler(value = InvalidMailException.class)
	public ResponseEntity<ErrorResponse> mailExceptionHandle() {
		ErrorResponse response = ErrorResponse.of(INVALID_MAIL);
		return new ResponseEntity<>(response, BAD_REQUEST);
	}

	@ExceptionHandler(value = UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> unauthorizedExceptionHandle() {
		ErrorResponse response = ErrorResponse.of(INVALID_CODE);
		return new ResponseEntity<>(response, UNAUTHORIZED);
	}
}
