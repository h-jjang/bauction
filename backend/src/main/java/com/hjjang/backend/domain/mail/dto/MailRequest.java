package com.hjjang.backend.domain.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailRequest {

	private String mail;

	private String code;
}
