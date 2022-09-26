package com.hjjang.backend.domain.chat.dto;

import com.hjjang.backend.domain.chat.domain.entity.ChatMessageEmoji;
import com.hjjang.backend.domain.chat.domain.entity.ChatMessageImage;
import com.hjjang.backend.domain.chat.domain.entity.ChatMessageText;
import com.hjjang.backend.domain.chat.domain.entity.EntranceChatRoom;
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

    public TradeChatRoomEntranceInfo(EntranceChatRoom entranceChatRoom) {
        this.tradeChatRoomInfo = new TradeChatRoomInfo(entranceChatRoom.getChatRoom());
        if (entranceChatRoom.getLastChatMessage() instanceof ChatMessageText) {
            this.lastChatMessageInfo = new ChatMessageInfo((ChatMessageText) entranceChatRoom.getLastChatMessage());
        } else if (entranceChatRoom.getLastChatMessage() instanceof ChatMessageEmoji) {
            this.lastChatMessageInfo = new ChatMessageInfo((ChatMessageEmoji) entranceChatRoom.getLastChatMessage());
        } else if (entranceChatRoom.getLastChatMessage() instanceof ChatMessageImage) {
            this.lastChatMessageInfo = new ChatMessageInfo((ChatMessageImage) entranceChatRoom.getLastChatMessage());
        }

    }
}
