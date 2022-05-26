package com.hjjang.backend.domain.chat.dto;

import com.hjjang.backend.domain.chat.domain.entity.ChatMessageEmoji;
import com.hjjang.backend.domain.chat.domain.entity.ChatMessageImage;
import com.hjjang.backend.domain.chat.domain.entity.ChatMessageText;
import com.hjjang.backend.domain.chat.domain.entity.ChatRoomEntrance;
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
    private ChatMessageInfo lastChatMessageInfo;

    // 안 읽은 메시지 수

    public TradeChatRoomEntranceInfo(ChatRoomEntrance chatRoomEntrance) {
        this.tradeChatRoomInfo = new TradeChatRoomInfo(chatRoomEntrance.getChatRoom());
        if (chatRoomEntrance.getLastChatMessage() instanceof ChatMessageText) {
            this.lastChatMessageInfo = new ChatMessageInfo((ChatMessageText) chatRoomEntrance.getLastChatMessage());
        } else if (chatRoomEntrance.getLastChatMessage() instanceof ChatMessageEmoji) {
            this.lastChatMessageInfo = new ChatMessageInfo((ChatMessageEmoji) chatRoomEntrance.getLastChatMessage());
        } else if (chatRoomEntrance.getLastChatMessage() instanceof ChatMessageImage) {
            this.lastChatMessageInfo = new ChatMessageInfo((ChatMessageImage) chatRoomEntrance.getLastChatMessage());
        }

    }
}
