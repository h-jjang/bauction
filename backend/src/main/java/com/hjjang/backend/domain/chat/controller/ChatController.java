package com.hjjang.backend.domain.chat.controller;


import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/chatroom")
@RestController
public class ChatController {


    // 채팅방 생성
    @PostMapping
    public void createChatRoom(){

    }

    // 채팅 방 리스트 조회 / 페이징
    @GetMapping
    public void readPagingChatRoomList(){

    }

    // 채팅 방 신고하기
    @PostMapping("/report")
    public void reportChatRoom(){

    }


    // 규정을 어길 시 채팅 방 삭제하기
    @DeleteMapping("/{chatRoomId}")
    public void hideChatRoom(){

    }



}
