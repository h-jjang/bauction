package com.hjjang.backend.domain.email.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailRequest {

	private String email;
	private String code;
}
