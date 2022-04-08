package com.hjjang.backend.domain.email.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {

	private String address;
	private String title;
	private String message;
}
