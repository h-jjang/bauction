package com.hjjang.backend.domain.mail.controller;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

import com.hjjang.backend.domain.mail.dto.MailRequest;
import com.hjjang.backend.domain.mail.dto.MailResponse;
import com.hjjang.backend.domain.mail.service.MailService;
import com.hjjang.backend.global.dto.ApiResponse;
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
	public ResponseEntity<ApiResponse> send(@RequestBody MailRequest mailRequest) {
		String mail = mailRequest.getMail();
		MailResponse mailResponse = mailService.sendMail(mail);
		return status(CREATED)
			.body(ApiResponse.success("send", mailResponse));
	}

	@PostMapping("/check")
	public ResponseEntity<ApiResponse> auth(@RequestBody MailRequest mailRequest) {
		MailResponse mailResponse = mailService.checkCode(mailRequest);
		return ok(ApiResponse.success("auth", mailResponse));
	}
}
