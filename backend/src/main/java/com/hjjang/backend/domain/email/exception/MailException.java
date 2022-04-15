package com.hjjang.backend.domain.email.exception;

import com.hjjang.backend.domain.email.dto.MailRequest;
import java.util.regex.Pattern;

public class MailException extends RuntimeException {

	private static final String UNIVERSITY_EMAIL_REGEX = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.+a+c+\\.+k+r+$";
	private static final String INVALID_EMAIL_MESSAGE = "알맞은 대학교 이메일 계정으로 입력해주세요.";
	private static final String INVALID_CODE_MESSAGE = "잘못된 코드 번호가 입력되었습니다.";
	private static final String INVALID_REQUEST_EMAIL_MESSAGE = "잘못된 요청의 이메일입니다.";
	private static final String TIME_LIMIT_MESSAGE = "시간이 초과되었습니다.";

	private MailException() {
	}

	public static void checkEmailPossible(String email) {
		checkEmailDuplicate(email);
		if (!isUniversityEmail(email)) {
			throw new RuntimeException(INVALID_EMAIL_MESSAGE);
		}
	}

	public static void checkRequest(MailRequest mailRequest, String code, String email) {
		checkValidCode(mailRequest, code);
		checkValidRequestEmail(mailRequest, email);
	}

	private static void checkValidRequestEmail(MailRequest mailRequest, String email) {
		if (!mailRequest.getEmail().equals(email)) {
			throw new RuntimeException(INVALID_REQUEST_EMAIL_MESSAGE);
		}
	}

	private static void checkValidCode(MailRequest mailRequest, String code) {
		checkTimeLimit(code);
		if (!mailRequest.getCode().equals(code)) {
			throw new RuntimeException(INVALID_CODE_MESSAGE);
		}
	}

	private static void checkTimeLimit(String code) {
		// 시간 지난 만큼을 인자로 받아 넘었을 경우 exception
	}

	private static boolean isUniversityEmail(String email) {
		return Pattern.matches(UNIVERSITY_EMAIL_REGEX, email);
	}

	private static void checkEmailDuplicate(String email) {
		// 모든 유저에 저장되어 있는 email과 겹친다면 exception
	}
}
