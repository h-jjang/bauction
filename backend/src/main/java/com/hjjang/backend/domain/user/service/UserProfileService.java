package com.hjjang.backend.domain.user.service;

import com.hjjang.backend.domain.user.dto.UserProfileInfo;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.exception.UserNotFoundException;
import com.hjjang.backend.domain.user.repository.UserRepository;
import com.hjjang.backend.global.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;

    public UserProfileInfo getUserProfile(String userId) {

        //todo add exception
        User user = userRepository.findUserByProviderId(userId).orElseThrow(EntityNotFoundException::new);

        //todo found univName logic
        return UserProfileInfo.builder()
            .userNickname(user.getNickName())
            .userImageUrl(user.getImageUrl())
            .userMannerTemperature(user.getMannerTemperature())
            .userEmail(user.getEmail())
            .userUnivName("산기대")
            .build();
    }

    public User findById(Long id) {
        return Stream.of(id)
                .map(userRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
