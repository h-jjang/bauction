package com.hjjang.backend.domain.chat.dto;

import com.hjjang.backend.domain.chat.domain.entity.ChatMessageEmoji;
import com.hjjang.backend.domain.chat.domain.entity.ChatMessageImage;
import com.hjjang.backend.domain.chat.domain.entity.ChatMessageText;
import com.hjjang.backend.domain.user.dto.UserProfileInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatMessageInfo {

    private UserProfileInfo senderUserProfileInfo;

    private Long chatRoomId;

    // dtype 형식
    private String dtype;

    private Object content;

    // 안 읽은 메시지 수

    // chatMessageImage를 dto로 변환
    public ChatMessageInfo(ChatMessageImage chatMessageImage) {
        this.senderUserProfileInfo = new UserProfileInfo(chatMessageImage.getSenderUser());
        this.chatRoomId = chatMessageImage.getId();
        this.dtype = chatMessageImage.getDtype();
        this.content = chatMessageImage.getImage();
    }

    // chatMessageEmoji를 dto로 변환
    public ChatMessageInfo(ChatMessageEmoji chatMessageEmoji) {
        this.senderUserProfileInfo = new UserProfileInfo(chatMessageEmoji.getSenderUser());
        this.chatRoomId = chatMessageEmoji.getId();
        this.dtype = chatMessageEmoji.getDtype();
        this.content = chatMessageEmoji.getEmoji();
    }

    // chatMessageText를 dto로 변환
    public ChatMessageInfo(ChatMessageText chatMessageText) {
        this.senderUserProfileInfo = new UserProfileInfo(chatMessageText.getSenderUser());
        this.chatRoomId = chatMessageText.getId();
        this.dtype = chatMessageText.getDtype();
        this.content = chatMessageText.getChatMessageContent();
    }
}
