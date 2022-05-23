package com.hjjang.backend.domain.chat.controller;


import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/chatmessage")
@RestController
public class ChatMessageController {


    // 일반 채팅 보내기
    @PostMapping("/text")
    public void chatByText(){

    }

    // 사진 채팅 보내기
    @GetMapping("/image")
    public void chatByImage(){

    }

    // 이모티콘 채팅 보내기
    @PostMapping("/emoji")
    public void chatByEmoji(){

    }


    // 메시지 숨기기
    @DeleteMapping("/")
    public void hideMessage(){

    }

    // 메시지 조회 / 페이징
    @GetMapping("/{chatRoomId}")
    public void readMessageByChatRoom(){

    }


}
