package com.hjjang.backend.domain.chat.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("emoji")
@Table(name = "chat_message_emoji")
public class ChatMessageEmoji extends ChatMessage {

    @Column(name = "chat_message_emoji_content")
    private Emoji emoji;


}
