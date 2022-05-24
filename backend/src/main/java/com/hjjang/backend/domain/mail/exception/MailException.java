package com.hjjang.backend.domain.mail.exception;

import com.hjjang.backend.domain.mail.dto.Mail;
import com.hjjang.backend.domain.mail.domain.MailRegex;
import com.hjjang.backend.domain.mail.dto.MailRequest;
import java.util.regex.Pattern;

public class MailException extends RuntimeException {

	private MailException() { }

	public static void checkPossibleMail(String mailAddress) {
		checkMailDuplicate(mailAddress);
		if (!isUniversityMail(mailAddress)) {
			throw new MailException();
		}
	}

	public static void checkRequest(MailRequest mailRequest, Mail mail) {
		UnauthorizedException.checkValidCode(mailRequest, mail.getCode());
		checkValidRequestMail(mailRequest, mail.getAddress());
	}

	private static void checkValidRequestMail(MailRequest mailRequest, String mailAddress) {
		if (!mailRequest.getMail().equals(mailAddress)) {
			throw new MailException();
		}
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
