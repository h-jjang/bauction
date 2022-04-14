package com.hjjang.backend.domain.email.service;

import com.hjjang.backend.domain.email.dto.MailRequest;
import com.hjjang.backend.domain.email.dto.MailResponse;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

	private static final String TITLE = "[Bauction] 인증번호를 발송했습니다.";
	private static final String MESSAGE = "본인확인 인증 메일\n"
		+ "이메일 인증을 진행해주세요.\n"
		+ "아래 메일 인증 번호를 입력하여 회원가입을 완료하세요.\n";
	private final JavaMailSender javaMailSender;
	private String code;

	public MailResponse sendMail(String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(TITLE);
		code = createRandomCode();
		message.setText(MESSAGE + code);
		javaMailSender.send(message);
		return new MailResponse(false);
	}

	public MailResponse checkCode(MailRequest mailRequest) {
		if (mailRequest.getCode().equals(code)) {
			return new MailResponse(true);
		}
		// 유효하지 않은 코드 Exception
		return null;
	}

	private String createRandomCode() {
		Random random = new Random();
		StringBuilder buffer = new StringBuilder();
		while (buffer.length() < 6) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}
}
