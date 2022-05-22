package com.hjjang.backend.domain.chat.domain.repository;

import com.hjjang.backend.domain.chat.domain.entity.ChatMessageText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageTextRepository extends JpaRepository<ChatMessageText, Long> {

}
