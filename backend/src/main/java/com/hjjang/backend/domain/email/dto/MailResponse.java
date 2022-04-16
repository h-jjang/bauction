package com.hjjang.backend.domain.email.dto;

import com.hjjang.backend.domain.email.domain.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailResponse {

	private Email email;
}
