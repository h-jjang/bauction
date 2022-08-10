package com.hjjang.backend.domain.mail.service;

import static com.hjjang.backend.global.response.code.ErrorCode.*;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hjjang.backend.domain.mail.domain.MailMessage;
import com.hjjang.backend.domain.mail.domain.MailRegex;
import com.hjjang.backend.domain.mail.dto.Mail;
import com.hjjang.backend.domain.mail.dto.MailRequest;
import com.hjjang.backend.domain.mail.dto.MailResponse;
import com.hjjang.backend.domain.mail.exception.InvalidMailException;
import com.hjjang.backend.domain.mail.exception.UnauthorizedException;
import com.hjjang.backend.domain.university.entity.University;
import com.hjjang.backend.domain.university.service.UniversityService;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.repository.UserRepository;
import com.hjjang.backend.global.util.UserUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

	private final UniversityService universityService;
	private final UserRepository userRepository;
	private final JavaMailSender javaMailSender;
	private final Mail mail = new Mail();
	private final UserUtil userUtil;

	@Async("mailExecutor")
	public void sendMail(String mailAddress) {
		checkPossibleMail(mailAddress);
		SimpleMailMessage message = new SimpleMailMessage();
		setMessage(message, mailAddress);
		javaMailSender.send(message);
	}

	public MailResponse checkCode(MailRequest mailRequest) {
		checkRequest(mailRequest);
		University university = universityService.findUniversityByName(mail.getUniversity());
		User user = userUtil.getLoginUserByToken();
		user.setEmailAndUniversity(mail.getAddress(), university);
		userRepository.save(user);
		return new MailResponse(mail);
	}

	private String parseUniversity(String mailAddress) {
		MailRegex mailParse = MailRegex.MAIL_PARSE;
		String regex = mailParse.getRegex();
		List<String> splitMail = List.of(mailAddress.split(regex));
		int universityIndex = splitMail.lastIndexOf("ac") - 1;
		return splitMail.get(universityIndex);
	}

	private void setMessage(SimpleMailMessage message, String mailAddress) {
		message.setTo(mailAddress);
		MailMessage title = MailMessage.AUTH_TITLE;
		message.setSubject(title.getContent());
		saveMailInfo(mailAddress);
		MailMessage mailMessage = MailMessage.AUTH_MESSAGE;
		message.setText(mailMessage.getContent() + mail.getCode());
	}

	private void saveMailInfo(String mailAddress) {
		mail.createRandomCode();
		mail.setAddress(mailAddress);
		String university = parseUniversity(mailAddress);
		mail.setUniversity(university);
		mail.setIsAuth(false);
	}

	private void checkPossibleMail(String mailAddress) {
		if (!isUniversityMail(mailAddress)) {
			throw new InvalidMailException();
		}
	}

	private void checkRequest(MailRequest mailRequest) {
		checkValidCode(mailRequest, mail.getCode());
		checkValidRequestMail(mailRequest, mail.getAddress());
		mail.setIsAuth(true);
	}

	private void checkValidCode(MailRequest mailRequest, String code) {
		if (!mailRequest.getCode().equals(code)) {
			throw new UnauthorizedException(INVALID_CODE);
		}
	}

	private void checkValidRequestMail(MailRequest mailRequest, String mailAddress) {
		if (!mailRequest.getMail().equals(mailAddress)) {
			throw new InvalidMailException();
		}
	}

	private boolean isUniversityMail(String mailAddress) {
		MailRegex university = MailRegex.UNIVERSITY;
		MailRegex gsUniversity = MailRegex.GS_UNIVERSITY;
		if (Pattern.matches(university.getRegex(), mailAddress)) {
			return true;
		}
		return Pattern.matches(gsUniversity.getRegex(), mailAddress);
	}
}
