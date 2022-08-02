package com.hjjang.backend.domain.chat.service;

import com.hjjang.backend.domain.chat.domain.entity.ChatMessageText;
import com.hjjang.backend.domain.chat.domain.entity.ChatRoom;
import com.hjjang.backend.domain.chat.domain.entity.ChatRoomUser;
import com.hjjang.backend.domain.chat.domain.repository.ChatMessageTextRepository;
import com.hjjang.backend.domain.chat.domain.repository.ChatRoomRepository;
import com.hjjang.backend.domain.chat.domain.repository.ChatRoomUserRepository;
import com.hjjang.backend.domain.chat.dto.ChatMessageInfo;
import com.hjjang.backend.domain.chat.dto.MessageRequest;
import com.hjjang.backend.domain.chat.exception.NotFoundChatRoomEntityException;
import com.hjjang.backend.domain.chat.exception.NotFoundChatRoomUserEntityException;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final UserUtil userUtil;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final ChatMessageTextRepository chatMessageTextRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    // 일반 채팅 보내기
    public void chatByText(Long chatRoomId, MessageRequest messageRequest) {
        User sender = userUtil.getLoginUserByToken();
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(NotFoundChatRoomEntityException::new);
        List<ChatRoomUser> chatRoomUsers = chatRoomUserRepository.findAllByChatRoomId(chatRoom.getId())
                .orElseThrow(NotFoundChatRoomUserEntityException::new);

        ChatMessageText newTextChatMessage = new ChatMessageText(sender, chatRoom, messageRequest.getMessage());
        ChatMessageText chatMessageText = chatMessageTextRepository.save(newTextChatMessage);

        chatRoomUsers.forEach(r -> simpMessagingTemplate.convertAndSend("/sub/" + r.getUser().getNickName(), new ChatMessageInfo(chatMessageText)));
    }

    // 사진 채팅 보내기
    public void chatByImage() {

    }

    // 이모티콘 채팅 보내기
    public void chatByEmoji() {

    }

    // 메시지 숨기기
    public void hideMessage() {

    }

    // 메시지 조회 / 페이징
    public void readMessageByChatRoom() {

    }

}
