package com.hjjang.backend.domain.user.exception.handler;

import com.hjjang.backend.domain.user.exception.NotFoundUserEntityException;
import com.hjjang.backend.global.response.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hjjang.backend.global.response.code.ErrorCode.USER_NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = NotFoundUserEntityException.class)
    public ResponseEntity<ErrorResponse> notFoundSellerEntityExceptionHandle(NotFoundUserEntityException e) {
        final ErrorResponse response = ErrorResponse.of(USER_NOT_FOUND);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }


}
