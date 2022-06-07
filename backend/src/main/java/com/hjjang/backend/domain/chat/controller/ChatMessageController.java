package com.hjjang.backend.domain.chat.controller;


import com.hjjang.backend.domain.chat.dto.MessageRequest;
import com.hjjang.backend.domain.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    // 일반 채팅 보내기
    @MessageMapping("/text/{roomId}")
    public void chatByText(@PathVariable("roomId") Long roomId, @RequestBody MessageRequest messageRequest) {
        chatMessageService.chatByText(roomId, messageRequest);
    }

    // 사진 채팅 보내기
    @MessageMapping("/image")
    public void chatByImage() {

    }

    // 이모티콘 채팅 보내기
    @MessageMapping("/emoji")
    public void chatByEmoji() {

    }


    // 메시지 숨기기
    @DeleteMapping
    public void hideMessage() {

    }

    // 메시지 조회 / 페이징
    @GetMapping("/{chatRoomId}")
    public void readMessageByChatRoom() {

    }


}
