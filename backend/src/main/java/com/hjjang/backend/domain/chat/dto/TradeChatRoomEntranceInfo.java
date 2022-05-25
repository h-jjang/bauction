package com.hjjang.backend.domain.chat.dto;

import com.hjjang.backend.domain.chat.domain.entity.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TradeChatRoomEntranceInfo {

    // 채팅방의 정보
    private TradeChatRoomInfo tradeChatRoomInfo;

    // 제일 최근 메시지
    private ChatMessage lastChatMessage;

    // 안 읽은 메시지 수
}
