package com.hjjang.backend.domain.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Emoji {
    SMILE("스마일", "스마일 이모지 url 입니다."),
    CRY("울음", "울음 이모지 url 입니다."),
    MAD("화남", "화남 이모지 url 입니다.");

    private final String name;
    private final String url;


}
