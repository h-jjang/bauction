package com.hjjang.backend.domain.mail.exception;

import com.hjjang.backend.domain.mail.dto.Mail;
import com.hjjang.backend.domain.mail.domain.MailRegex;
import com.hjjang.backend.domain.mail.domain.ExceptionMessage;
import com.hjjang.backend.domain.mail.dto.MailRequest;
import java.util.regex.Pattern;

public class MailException extends RuntimeException {

	private MailException() {
	}

	public static void checkPossibleMail(String mailAddress) {
		checkMailDuplicate(mailAddress);
		if (!isUniversityMail(mailAddress)) {
			ExceptionMessage invalidMail = ExceptionMessage.INVALID_MAIL;
			throw new RuntimeException(invalidMail.getMessage());
		}
	}

	public static void checkRequest(MailRequest mailRequest, Mail mail) {
		checkValidCode(mailRequest, mail.getCode());
		checkValidRequestMail(mailRequest, mail.getAddress());
	}

	private static void checkValidRequestMail(MailRequest mailRequest, String mailAddress) {
		if (!mailRequest.getMail().equals(mailAddress)) {
			ExceptionMessage invalidRequestMail = ExceptionMessage.INVALID_REQUEST_MAIL;
			throw new RuntimeException(invalidRequestMail.getMessage());
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

	private static boolean isUniversityMail(String mailAddress) {
		MailRegex university = MailRegex.UNIVERSITY;
		MailRegex gsUniversity = MailRegex.GS_UNIVERSITY;
		if (Pattern.matches(university.getRegex(), mailAddress)) {
			return true;
		}
		return Pattern.matches(gsUniversity.getRegex(), mailAddress);
	}

	private static void checkMailDuplicate(String mail) {
		// 모든 유저에 저장되어 있는 email과 겹친다면 exception
	}
}
