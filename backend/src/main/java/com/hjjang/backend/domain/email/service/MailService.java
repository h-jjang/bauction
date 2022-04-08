package com.hjjang.backend.domain.email.service;

import com.hjjang.backend.domain.email.dto.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

	private JavaMailSender javaMailSender;

	public void mailSend(MailDto mailDto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailDto.getAddress());
		message.setSubject(mailDto.getTitle());
		message.setText(mailDto.getMessage());
		javaMailSender.send(message);
	}
}
