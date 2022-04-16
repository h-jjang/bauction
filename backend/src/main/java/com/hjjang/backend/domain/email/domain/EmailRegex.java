package com.hjjang.backend.domain.email.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailRegex {
	UNIVERSITY("UNIVERSITY_EMAIL_REGEX", "^[a-zA-Z0-9]+@[a-zA-Z0-9.]+\\.+a+c+\\.+k+r+$"),
	EMAIL_PARSE("EMAIL_PARSE_REGEX", "[@.]");

	private final String name;
	private final String regex;
}
