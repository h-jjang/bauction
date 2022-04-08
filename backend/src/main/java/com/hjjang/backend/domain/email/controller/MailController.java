package com.hjjang.backend.domain.email.controller;

import com.hjjang.backend.domain.email.dto.MailDto;
import com.hjjang.backend.domain.email.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MailController {

	private final MailService mailService;

	@PostMapping("/mail")
	public void executeMail(MailDto mailDto) {
		mailService.mailSend(mailDto);
	}
}
