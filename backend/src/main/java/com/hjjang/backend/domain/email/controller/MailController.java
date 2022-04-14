package com.hjjang.backend.domain.email.controller;

import com.hjjang.backend.domain.email.dto.MailRequest;
import com.hjjang.backend.domain.email.dto.MailResponse;
import com.hjjang.backend.domain.email.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

	private final MailService mailService;

	@PostMapping("/auth")
	public ResponseEntity<MailResponse> sendMail(@RequestBody MailRequest mailRequest) {
		return ResponseEntity.ok().body(mailService.sendMail(mailRequest.getEmail()));
	}

	@PostMapping("/check")
	public ResponseEntity<MailResponse> checkCode(@RequestBody MailRequest mailRequest) {
		return ResponseEntity.ok().body(mailService.checkCode(mailRequest));
	}
}
