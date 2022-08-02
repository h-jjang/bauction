package com.hjjang.backend.domain.chat.domain.repository;

import com.hjjang.backend.domain.chat.domain.entity.EntranceChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntranceChatRoomRepository extends JpaRepository<EntranceChatRoom, Long> {

    Page<EntranceChatRoom> findAllByUserId(Long userId, Pageable pageable);
}
