package com.hjjang.backend.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class HideTradeChatRoomResponse {

    private Long chatRoomId;
    private Boolean isSuccessed;
}
