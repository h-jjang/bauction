package com.hjjang.backend.domain.email.service;

import com.hjjang.backend.domain.email.domain.Email;
import com.hjjang.backend.domain.email.dto.MailRequest;
import com.hjjang.backend.domain.email.dto.MailResponse;
import com.hjjang.backend.domain.email.exception.MailException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;
	private final Email email = new Email();

	private static final String TITLE = "[Bauction] 인증번호를 발송했습니다.";
	private static final String EMAIL_MESSAGE = "본인확인 인증 메일\n"
		+ "이메일 인증을 진행해주세요.\n"
		+ "아래 메일 인증 번호를 입력하여 회원가입을 완료하세요.\n";
	private static final String EMAIL_PARSE_REGEX = "[@.]";

	public MailResponse sendMail(String emailAddress) {
		MailException.checkEmailPossible(emailAddress);
		SimpleMailMessage message = new SimpleMailMessage();
		setMessage(message, emailAddress);
		javaMailSender.send(message);
		return new MailResponse(email);
	}

	public MailResponse checkCode(MailRequest mailRequest) {
		MailException.checkRequest(mailRequest, email);
		email.saveAuthStatus(true);
		return new MailResponse(email);
	}

	private String parseUniversity(String email) {
		List<String> split = List.of(email.split(EMAIL_PARSE_REGEX));
		return split.get(1);
	}

	private void setMessage(SimpleMailMessage message, String emailAddress) {
		message.setTo(emailAddress);
		message.setSubject(TITLE);
		saveEmailInfo(emailAddress);
		message.setText(EMAIL_MESSAGE + email.getCode());
	}

	private void saveEmailInfo(String emailAddress) {
		String code = email.createRandomCode();
		email.saveCode(code);
		email.saveAddress(emailAddress);
		String university = parseUniversity(emailAddress);
		email.saveUniversity(university);
		email.saveAuthStatus(false);
	}
}
