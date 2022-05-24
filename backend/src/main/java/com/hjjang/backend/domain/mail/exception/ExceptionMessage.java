package com.hjjang.backend.domain.mail.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
	INVALID_MAIL("알맞은 대학교 메일 계정으로 입력해주세요."),
	INVALID_CODE("잘못된 코드 번호가 입력되었습니다."),
	TIME_LIMIT("시간이 초과되었습니다.");

	private final String message;
}
