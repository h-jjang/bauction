package com.hjjang.backend.domain.email.service;

import com.hjjang.backend.domain.email.dto.MailRequest;
import com.hjjang.backend.domain.email.dto.MailResponse;
import com.hjjang.backend.domain.email.exception.MailException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;

	private static final String TITLE = "[Bauction] 인증번호를 발송했습니다.";
	private static final String EMAIL_MESSAGE = "본인확인 인증 메일\n"
		+ "이메일 인증을 진행해주세요.\n"
		+ "아래 메일 인증 번호를 입력하여 회원가입을 완료하세요.\n";
	private static final String EMAIL_PARSE_REGEX = "[@.]";
	private static final int CODE_SIZE = 6;
	private String code;
	private String email;
	private String university;

	public MailResponse sendMail(String email) {
		MailException.checkEmailPossible(email);
		SimpleMailMessage message = new SimpleMailMessage();
		setMessage(message, email);
		javaMailSender.send(message);
		university = parseUniversity(email);
		return new MailResponse(false, university);
	}

	public MailResponse checkCode(MailRequest mailRequest) {
		MailException.checkRequest(mailRequest, code, email);
		return new MailResponse(true, university);
	}

	private String parseUniversity(String email) {
		List<String> split = List.of(email.split(EMAIL_PARSE_REGEX));
		return split.get(1);
	}

	private void setMessage(SimpleMailMessage message, String email) {
		message.setTo(email);
		message.setSubject(TITLE);
		code = createRandomCode();
		this.email = email;
		message.setText(EMAIL_MESSAGE + code);
	}

	private String createRandomCode() {
		Random random = new Random();
		StringBuilder buffer = new StringBuilder();
		while (buffer.length() < CODE_SIZE) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}
}
