package com.hjjang.backend.domain.mail.exception;

public class TimeLimitException extends RuntimeException {

	private TimeLimitException() { }

	public static void checkTimeLimit(String code) {
		// 시간 지난 만큼을 인자로 받아 넘었을 경우 exception
	}
}
