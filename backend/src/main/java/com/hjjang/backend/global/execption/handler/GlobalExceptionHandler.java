package com.hjjang.backend.global.execption.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 500 에러
//    @ExceptionHandler
//    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
//        final ErrorResponse response = ErrorResponse.of(INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
