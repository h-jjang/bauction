package com.hjjang.backend.domain.user.service;

import com.hjjang.backend.domain.user.dto.UserProfileDTO;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;

    public UserProfileDTO getUserProfile(String userId) {

        //todo add exception
        User user = userRepository.findUserByProviderId(userId).orElseThrow(EntityNotFoundException::new);

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
