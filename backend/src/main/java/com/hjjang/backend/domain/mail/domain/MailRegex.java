package com.hjjang.backend.domain.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailRegex {
	UNIVERSITY("UNIVERSITY_MAIL_REGEX", "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.+a+c+\\.+k+r+$"),
	GS_UNIVERSITY("GS_UNIVERSITY_MAIL_REGEX", "^[a-zA-Z0-9]+@+g+s+\\.+[a-zA-Z0-9]+\\.+a+c+\\.+k+r+$"),
	MAIL_PARSE("MAIL_PARSE_REGEX", "[@.]");

	private final String name;
	private final String regex;
}
