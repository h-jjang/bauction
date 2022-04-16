package com.hjjang.backend.domain.email.domain;

import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Email {

	private String code;
	private String address;
	private String university;
	private Boolean isAuth;

	private static final int CODE_SIZE = 6;

	public String createRandomCode() {
		Random random = new Random();
		StringBuilder buffer = new StringBuilder();
		while (buffer.length() < CODE_SIZE) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}

	public void saveCode(String code) {
		this.code = code;
	}

	public void saveAddress(String address) {
		this.address = address;
	}

	public void saveUniversity(String university) {
		this.university = university;
	}

	public void saveAuthStatus(boolean isAuth) {
		this.isAuth = isAuth;
	}
}
