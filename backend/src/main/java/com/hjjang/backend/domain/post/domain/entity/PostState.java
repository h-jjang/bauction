package com.hjjang.backend.domain.post.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostState {
    SALE("판매중"),
    RESERVED("예약중"),
    SOLD("판매완료"),
    ;

    final String state;
}
