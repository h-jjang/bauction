package com.hjjang.backend.domain.mail.controller;

import com.hjjang.backend.domain.mail.dto.MailRequest;
import com.hjjang.backend.domain.mail.dto.MailResponse;
import com.hjjang.backend.domain.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class MailController {

	private final MailService mailService;

	@PostMapping("/auth")
	public ResponseEntity<MailResponse> send(@RequestBody MailRequest mailRequest) {
		String email = mailRequest.getMail();
		MailResponse mailResponse = mailService.sendMail(email);
		return ResponseEntity.ok().body(mailResponse);
	}

	@PostMapping("/check")
	public ResponseEntity<MailResponse> auth(@RequestBody MailRequest mailRequest) {
		MailResponse mailResponse = mailService.checkCode(mailRequest);
		return ResponseEntity.ok().body(mailResponse);
	}
}
