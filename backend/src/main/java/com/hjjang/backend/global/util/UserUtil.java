package com.hjjang.backend.global.util;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.hjjang.backend.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public static String getLoginUserIdByToken() {
        User principal = (User)SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
        return principal.getUsername();
    }

    public com.hjjang.backend.domain.user.entity.User getLoginUserByToken() {

        User principal = (User)SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
        return userRepository.findById(principal.getUsername()).orElseThrow(EntityNotFoundException::new);
    }
}
