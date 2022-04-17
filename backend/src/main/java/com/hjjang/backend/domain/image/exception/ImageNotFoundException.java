package com.hjjang.backend.domain.image.exception;

public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException() {
        super();
    }

    public ImageNotFoundException(String message) {
        super(message);
    }

    public ImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
