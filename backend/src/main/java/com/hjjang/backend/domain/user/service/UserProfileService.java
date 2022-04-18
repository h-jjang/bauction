package com.hjjang.backend.domain.user.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.hjjang.backend.domain.user.dto.UserProfileDTO;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.repository.UserRepository;
import com.hjjang.backend.global.util.UserUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;

    public UserProfileDTO getUserProfile() {
        String userId = UserUtil.getLoginUserIdByToken();

        //todo add exception
        User user = userRepository.findUserById(userId).orElseThrow(EntityNotFoundException::new);

        //todo found univName logic
        return UserProfileDTO.builder()
            .userNickname(user.getNickName())
            .userImageUrl(user.getImageUrl())
            .userMannerTemperature(user.getMannerTemperature())
            .userEmail(user.getEmail())
            .userUnivName("산기대")
            .build();
    }
}
