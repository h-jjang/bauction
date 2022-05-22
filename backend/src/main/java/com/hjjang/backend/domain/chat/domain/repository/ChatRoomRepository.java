package com.hjjang.backend.domain.chat.domain.repository;

import com.hjjang.backend.domain.chat.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
