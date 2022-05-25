package com.hjjang.backend.domain.mail.controller;

import static com.hjjang.backend.global.response.code.SuccessCode.*;

import com.hjjang.backend.domain.mail.dto.MailRequest;
import com.hjjang.backend.domain.mail.dto.MailResponse;
import com.hjjang.backend.domain.mail.service.MailService;
import com.hjjang.backend.global.response.response.SuccessResponse;
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
	public ResponseEntity<SuccessResponse> send(@RequestBody MailRequest mailRequest) {
		String mail = mailRequest.getMail();
		MailResponse mailResponse = mailService.sendMail(mail);
		return ResponseEntity.ok(SuccessResponse.of(MAIL_SEND_SUCCESS, mailResponse));
	}

	@PostMapping("/check")
	public ResponseEntity<SuccessResponse> auth(@RequestBody MailRequest mailRequest) {
		MailResponse mailResponse = mailService.checkCode(mailRequest);
		return ResponseEntity.ok(SuccessResponse.of(MAIL_CHECK_SUCCESS, mailResponse));
	}
}
