package com.hjjang.backend.domain.chat.domain;

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
@DiscriminatorValue("text")
@Table(name = "chat_message_text")
public class ChatMessageText extends ChatMessage {

    @Column(name = "chat_message_text_content")
    private String chatMessageContent;

}
