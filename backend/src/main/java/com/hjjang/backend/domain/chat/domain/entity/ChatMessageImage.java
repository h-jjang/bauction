package com.hjjang.backend.domain.chat.domain.entity;

import com.hjjang.backend.domain.image.domain.entity.Image;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("image")
@Table(name = "chat_message_image")
public class ChatMessageImage extends ChatMessage {

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;


}
