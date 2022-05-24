package com.hjjang.backend.domain.chat.controller;


import com.hjjang.backend.domain.chat.dto.CreateTradeChatRoomResponse;
import com.hjjang.backend.domain.chat.service.ChatRoomService;
import com.hjjang.backend.global.response.response.SuccessResponse;
import com.hjjang.backend.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hjjang.backend.global.response.code.SuccessCode.CREATE_CHAT_ROOM_SUCCESS;

@RequestMapping("/api/chatroom")
@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final UserUtil userUtil;

    // 채팅방 생성 test를 위한 api
    // 채팅방은 거래 시작 혹은 경매 시작과 동시에 생성되기 때문
    @PostMapping("/trade/{sellerId}")
    public ResponseEntity<SuccessResponse> createChatRoom(@PathVariable Long sellerId) {
        CreateTradeChatRoomResponse createTradeChatRoomResponse
                = chatRoomService.createChatTradeRoomBySellerId(sellerId);
        return ResponseEntity.ok(SuccessResponse.of(CREATE_CHAT_ROOM_SUCCESS, createTradeChatRoomResponse));
    }

    // 채팅 방 리스트 조회 / 페이징
    @GetMapping
    public void readPagingChatRoomList() {

    }

    // 채팅 방 신고하기
    @PostMapping("/report")
    public void reportChatRoom() {

    }


    // 규정을 어길 시 채팅 방 삭제하기
    @DeleteMapping("/{chatRoomId}")
    public void hideChatRoom() {

    }


}
