package com.hjjang.backend.email;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.hjjang.backend.domain.mail.dto.Mail;
import com.hjjang.backend.domain.mail.dto.MailRequest;
import com.hjjang.backend.domain.mail.exception.InvalidMailException;
import com.hjjang.backend.domain.mail.service.MailService;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;

class MailServiceTest {

	private final MailService mailService = new MailService(mailSender());

	@DisplayName("코드 인증 테스트")
	@Test
	void authEmail() {
		//given
		String emailAddress = "32174294@dankook.ac.kr";
		String code = "123456";
		Mail email = new Mail(code, emailAddress, "dankook", false);
		//when
		MailRequest mailRequest = new MailRequest(emailAddress, code);

		//then
		assertThat(email.getCode()).isEqualTo(mailRequest.getCode());
	}

	@DisplayName("이메일에서 대학교 파싱 테스트")
	@Test
	void parseUniversityFromEmail() throws Exception {
		//reflection
		Method parseUniversity = MailService.class.getDeclaredMethod("parseUniversity", String.class);
		//given
		String email = "32174294@dankook.ac.kr";
		//when
		parseUniversity.setAccessible(true);
		String university = (String) parseUniversity.invoke(mailService, email);
		//then
		assertThat(university).isEqualTo("dankook");
	}

	@DisplayName("gs이메일에서 대학교 파싱 테스트")
	@Test
	void parseGsUniversityFromEmail() throws Exception {
		//reflection
		Method parseUniversity = MailService.class.getDeclaredMethod("parseUniversity", String.class);
		//given
		String email = "dr0joon@gs.anyang.ac.kr";
		//when
		parseUniversity.setAccessible(true);
		String university = (String) parseUniversity.invoke(mailService, email);
		//then
		assertThat(university).isEqualTo("anyang");
	}

	@DisplayName("메일 주소 예외처리 테스트 (실패 케이스)")
	@Test
	void checkInvalidEmail() {
		//given
		List<String> falseEmails = new ArrayList<>();
		falseEmails.add("32174294@naver.com");
		falseEmails.add("32174294@gmail.com");
		falseEmails.add("32174294_dankook.ac.kr");
		falseEmails.add("@dankook.ac.kr");
		falseEmails.add("32174294@ac.kr");
		falseEmails.add("32174294@.ac.kr");
		falseEmails.add("32174294@gsd.dankook.ac.kr");
		//when
		//then
//		falseEmails.forEach(MailServiceTest::isException);
	}

	@DisplayName("메일 주소 예외처리 테스트 (성공 케이스)")
	@Test
	void checkValidEmail() {
		//given
		List<String> trueEmails = new ArrayList<>();
		trueEmails.add("32174294@dankook.ac.kr");
		trueEmails.add("32174294@anyang.ac.kr");
		trueEmails.add("32174294@kpu.ac.kr");
		trueEmails.add("dr0joon@gs.anyang.ac.kr");
		//when
		//then
//		trueEmails.forEach(InvalidMailException::checkPossibleMail);
	}

	private JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setProtocol("SMTP");
//		javaMailSender.setHost("127.0.0.1");
//		javaMailSender.setPort(587);
		return javaMailSender;
	}

//	private static void isException(String email) {
//		//then, when
//		assertThrows(RuntimeException.class, () ->
//
//			InvalidMailException.checkPossibleMail(email));
//	}
}
