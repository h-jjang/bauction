package com.hjjang.backend.domain.chat.domain.repository;

import com.hjjang.backend.domain.chat.domain.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
