package com.hjjang.backend.domain.user.controller;

import com.hjjang.backend.domain.user.dto.UserProfileInfo;
import com.hjjang.backend.domain.user.service.UserProfileService;
import com.hjjang.backend.global.response.code.SuccessCode;
import com.hjjang.backend.global.response.response.SuccessResponse;
import com.hjjang.backend.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserProfileService userProfileService;

    @GetMapping("/profile")
    public ResponseEntity<SuccessResponse> getProfile() {
        String userId = UserUtil.getLoginUserIdByToken();
        UserProfileInfo userProfile = userProfileService.getUserProfile(userId);
        return ResponseEntity.ok(SuccessResponse.of(SuccessCode.USER_PROFILE_SUCCESS, userProfile));
    }

}
