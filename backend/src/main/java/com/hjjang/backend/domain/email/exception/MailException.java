package com.hjjang.backend.domain.email.exception;

import java.util.regex.Pattern;

public class MailException extends RuntimeException {

	private static final String UNIV_EMAIL_REGEX = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.+a+c+\\.+k+r+$";

	private MailException(String message) {
		super(message);
	}

	public static void checkEmailPossible(String email) {
		checkEmailDuplicate(email);
		if (!isUnivEmail(email)) {
			throw new RuntimeException("알맞은 대학교 이메일 계정으로 입력해주세요.");
		}
	}

	private static boolean isUnivEmail(String email) {
		return Pattern.matches(UNIV_EMAIL_REGEX, email);
	}

	private static void checkEmailDuplicate(String email) {
		// 모든 유저에 저장되어 있는 email과 겹친다면 exception
	}
}
