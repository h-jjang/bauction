package com.hjjang.backend.domain.mail.dto;

import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mail {

	private String code;
	private String address;
	private String university;
	private Boolean isAuth;

	private static final int CODE_SIZE = 6;

	public void createRandomCode() {
		Random random = new Random();
		StringBuilder buffer = new StringBuilder();
		while (buffer.length() < CODE_SIZE) {
			buffer.append(random.nextInt(10));
		}
		code = buffer.toString();
	}
}
