package com.hjjang.backend.domain.chat.domain.repository;

import com.hjjang.backend.domain.chat.domain.entity.ChatMessageEmoji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageEmojiRepository extends JpaRepository<ChatMessageEmoji, Long> {

}
