package com.hjjang.backend.domain.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailMessage {
	AUTH_TITLE("[Bauction] 인증번호를 발송했습니다."),
	AUTH_MESSAGE("[본인확인 인증 메일]\n"
		+ "이메일 인증을 진행해주세요.\n"
		+ "아래 메일 인증 번호를 입력하여 회원가입을 완료하세요.\n"),
	KEYWORD_NOTICE_TITLE("[Bauction] 키워드 알림입니다."),
	KEYWORD_NOTICE_MESSAGE("[키워드 알림 메일]\n"
		+ "다음과 같은 키워드에 대한 게시글 알림입니다.\n"),
	TRADE_NOTICE_TITLE("[Bauction] 거래 현황 변동 알림입니다."),
	TRADE_NOTICE_MESSAGE("[거래 현황 변동 알림]\n"
		+ "다음 상품의 거래에 대한 현황이 변동되었습니다.\n")
	;

	private final String content;
}
