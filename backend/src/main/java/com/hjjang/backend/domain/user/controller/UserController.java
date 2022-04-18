package com.hjjang.backend.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.service.UserService;
import com.hjjang.backend.global.dto.ApiResponse;
import com.hjjang.backend.global.util.UserUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse getUser() {
        String userId = UserUtil.getLoginUserIdByToken();
        User user = userService.getUser(userId);
        return ApiResponse.success("user", user);
    }
}
