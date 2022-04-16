package com.hjjang.backend.email;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class EmailRegexTest {

	private static final String REGEX = "^[a-zA-Z0-9]+@[a-zA-Z0-9.]+\\.+a+c+\\.+k+r+$";
	private static final String EMAIL_PARSE_REGEX = "[@.]";

	@Test
	void parseUniversity() {
		//given
		String email = "32174294@dankook.ac.kr";
		String email2 = "dr0joon@gs.anyang.ac.kr";
		//when
		List<String> split = List.of(email.split(EMAIL_PARSE_REGEX));
		List<String> split2 = List.of(email2.split(EMAIL_PARSE_REGEX));
		int univIndex = split.lastIndexOf("ac") - 1;
		int univIndex2 = split2.lastIndexOf("ac") - 1;
		//then
		assertThat(split.get(univIndex)).isEqualTo("dankook");
		assertThat(split2.get(univIndex2)).isEqualTo("anyang");
	}

	@Test
	void checkInvalidEmail() {
		//given
		//when
		List<String> falseEmails = new ArrayList<>();
		falseEmails.add("32174294@naver.com");
		falseEmails.add("32174294@gmail.com");
		falseEmails.add("32174294_dankook.ac.kr");
		falseEmails.add("@dankook.ac.kr");
		falseEmails.add("32174294@ac.kr");
		falseEmails.add("32174294@.ac.kr");
		//then
		falseEmails.forEach(email -> {
			boolean match = Pattern.matches(REGEX, email);
			System.out.println(email + " : " + match);
			assertThat(match).isEqualTo(false);
		});
	}

	@Test
	void checkValidEmail() {
		//given
		//when
		List<String> trueEmails = new ArrayList<>();
		trueEmails.add("32174294@dankook.ac.kr");
		trueEmails.add("32174294@anyang.ac.kr");
		trueEmails.add("32174294@kpu.ac.kr");
		trueEmails.add("dr0joon@gs.anyang.ac.kr");
		//then
		trueEmails.forEach(email -> {
			boolean match = Pattern.matches(REGEX, email);
			System.out.println(email + " : " + match);
			assertThat(match).isEqualTo(true);
		});
	}
}
