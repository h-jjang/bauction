package com.hjjang.backend.domain.email.service;

import com.hjjang.backend.domain.email.domain.Email;
import com.hjjang.backend.domain.email.domain.EmailMessage;
import com.hjjang.backend.domain.email.domain.EmailRegex;
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
		EmailRegex emailParse = EmailRegex.EMAIL_PARSE;
		String regex = emailParse.getRegex();
		List<String> splitEmail = List.of(email.split(regex));
		int universityIndex = splitEmail.lastIndexOf("ac") - 1;
		return splitEmail.get(universityIndex);
	}

	private void setMessage(SimpleMailMessage message, String emailAddress) {
		message.setTo(emailAddress);
		EmailMessage title = EmailMessage.TITLE;
		message.setSubject(title.getContent());
		saveEmailInfo(emailAddress);
		EmailMessage emailMessage = EmailMessage.MESSAGE;
		message.setText(emailMessage.getContent() + email.getCode());
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
