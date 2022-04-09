package com.hjjang.backend.domain.category.exception;

public class CategoryNotFoundException extends RuntimeException {
	public CategoryNotFoundException() {
		super();
	}

	public CategoryNotFoundException(String message) {
		super(message);
	}

	public CategoryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
