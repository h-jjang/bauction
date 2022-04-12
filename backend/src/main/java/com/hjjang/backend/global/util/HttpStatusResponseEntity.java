package com.hjjang.backend.global.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpStatusResponseEntity {
    public static final ResponseEntity<HttpStatus> RESPONSE_OK = ResponseEntity.status(HttpStatus.OK).build();
}
