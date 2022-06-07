package com.hjjang.backend.domain.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailMessage {
	TITLE("[Bauction] 인증번호를 발송했습니다."),
	MESSAGE("본인확인 인증 메일\n"
		+ "이메일 인증을 진행해주세요.\n"
		+ "아래 메일 인증 번호를 입력하여 회원가입을 완료하세요.\n");

	private final String content;
}
