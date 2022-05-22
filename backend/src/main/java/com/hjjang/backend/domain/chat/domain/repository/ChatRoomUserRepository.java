package com.hjjang.backend.domain.chat.domain.repository;

import com.hjjang.backend.domain.chat.domain.entity.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {

}
