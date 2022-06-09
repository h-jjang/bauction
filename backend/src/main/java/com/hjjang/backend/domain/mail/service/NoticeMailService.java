package com.hjjang.backend.domain.mail.service;

import static com.hjjang.backend.domain.mail.domain.MailMessage.*;
import static com.hjjang.backend.global.response.code.ErrorCode.*;

import com.hjjang.backend.domain.mail.exception.UnauthorizedException;
import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeMailService {

	private final JavaMailSender javaMailSender;

	public void sendNotice(User user, Post post) {
		if (!user.getIsEmailVerification()) {
			throw new UnauthorizedException(NO_AUTHORITY);
		}
		SimpleMailMessage message = getSimpleMailMessage(user, post);
		javaMailSender.send(message);
	}

	private SimpleMailMessage getSimpleMailMessage(User user, Post post) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setSubject(TRADE_NOTICE_TITLE.getContent());
		message.setText(TRADE_NOTICE_MESSAGE.getContent() + post.getIsSaleCompletion().getState());
		return message;
	}
}
