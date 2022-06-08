package com.hjjang.backend.domain.trade.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TradeState {
    PENDING("대기"),
    RESERVE("예약"),
    CANCEL("취소"),
    APPROVE("판매완료"),
    ;

    private final String state;
}
