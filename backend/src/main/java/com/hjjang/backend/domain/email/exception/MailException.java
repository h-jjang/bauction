package com.hjjang.backend.domain.email.exception;

import com.hjjang.backend.domain.email.domain.Email;
import com.hjjang.backend.domain.email.domain.EmailRegex;
import com.hjjang.backend.domain.email.domain.ExceptionMessage;
import com.hjjang.backend.domain.email.dto.MailRequest;
import java.util.regex.Pattern;

public class MailException extends RuntimeException {

	private MailException() {
	}

	public static void checkEmailPossible(String email) {
		checkEmailDuplicate(email);
		if (!isUniversityEmail(email)) {
			ExceptionMessage invalidEmail = ExceptionMessage.INVALID_EMAIL;
			throw new RuntimeException(invalidEmail.getMessage());
		}
	}

	public static void checkRequest(MailRequest mailRequest, Email email) {
		checkValidCode(mailRequest, email.getCode());
		checkValidRequestEmail(mailRequest, email.getAddress());
	}

	private static void checkValidRequestEmail(MailRequest mailRequest, String email) {
		if (!mailRequest.getEmail().equals(email)) {
			ExceptionMessage invalidRequestEmail = ExceptionMessage.INVALID_REQUEST_EMAIL;
			throw new RuntimeException(invalidRequestEmail.getMessage());
		}
	}

	private static void checkValidCode(MailRequest mailRequest, String code) {
		checkTimeLimit(code);
		if (!mailRequest.getCode().equals(code)) {
			ExceptionMessage invalidCode = ExceptionMessage.INVALID_CODE;
			throw new RuntimeException(invalidCode.getMessage());
		}
	}

	private static void checkTimeLimit(String code) {
		// 시간 지난 만큼을 인자로 받아 넘었을 경우 exception
	}

	private static boolean isUniversityEmail(String email) {
		EmailRegex university = EmailRegex.UNIVERSITY;
		String universityRegex = university.getRegex();
		return Pattern.matches(universityRegex, email);
	}

	private static void checkEmailDuplicate(String email) {
		// 모든 유저에 저장되어 있는 email과 겹친다면 exception
	}
}
