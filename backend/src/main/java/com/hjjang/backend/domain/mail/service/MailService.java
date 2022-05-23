package com.hjjang.backend.domain.mail.service;

import com.hjjang.backend.domain.mail.dto.Mail;
import com.hjjang.backend.domain.mail.domain.MailMessage;
import com.hjjang.backend.domain.mail.domain.MailRegex;
import com.hjjang.backend.domain.mail.dto.MailRequest;
import com.hjjang.backend.domain.mail.dto.MailResponse;
import com.hjjang.backend.domain.mail.exception.MailException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;
	private final Mail mail = new Mail();

	public MailResponse sendMail(String mailAddress) {
		MailException.checkPossibleMail(mailAddress);
		SimpleMailMessage message = new SimpleMailMessage();
		setMessage(message, mailAddress);
		javaMailSender.send(message);
		return new MailResponse(mail);
	}

	public MailResponse checkCode(MailRequest mailRequest) {
		MailException.checkRequest(mailRequest, mail);
		mail.setIsAuth(true);
		return new MailResponse(mail);
	}

	private String parseUniversity(String mailAddress) {
		MailRegex mailParse = MailRegex.MAIL_PARSE;
		String regex = mailParse.getRegex();
		List<String> splitMail = List.of(mailAddress.split(regex));
		int universityIndex = splitMail.lastIndexOf("ac") - 1;
		return splitMail.get(universityIndex);
	}

	private void setMessage(SimpleMailMessage message, String mailAddress) {
		message.setTo(mailAddress);
		MailMessage title = MailMessage.TITLE;
		message.setSubject(title.getContent());
		saveMailInfo(mailAddress);
		MailMessage mailMessage = MailMessage.MESSAGE;
		message.setText(mailMessage.getContent() + mail.getCode());
	}

	private void saveMailInfo(String mailAddress) {
		String code = mail.createRandomCode();
		mail.setCode(code);
		mail.setAddress(mailAddress);
		String university = parseUniversity(mailAddress);
		mail.setUniversity(university);
		mail.setIsAuth(false);
	}
}
