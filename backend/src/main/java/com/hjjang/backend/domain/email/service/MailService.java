package com.hjjang.backend.domain.email.service;

import com.hjjang.backend.domain.email.dto.MailRequest;
import com.hjjang.backend.domain.email.dto.MailResponse;
import com.hjjang.backend.domain.email.exception.MailException;
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
	private static final int CODE_SIZE = 6;
	private String code;
	private String email;

	public MailResponse sendMail(String email) {
		MailException.checkEmailPossible(email);
		SimpleMailMessage message = new SimpleMailMessage();
		setMessage(message, email);
		javaMailSender.send(message);
		return new MailResponse(false);
	}

	private void setMessage(SimpleMailMessage message, String email) {
		message.setTo(email);
		message.setSubject(TITLE);
		code = createRandomCode();
		this.email = email;
		message.setText(EMAIL_MESSAGE + code);
	}

	public MailResponse checkCode(MailRequest mailRequest) {
		if (mailRequest.getCode().equals(code) & mailRequest.getEmail().equals(email)) {
			return new MailResponse(true);
		}
		return null;
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
