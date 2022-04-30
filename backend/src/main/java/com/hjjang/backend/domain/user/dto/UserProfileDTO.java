package com.hjjang.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {

    private String userNickname;

    private String userImageUrl;

    private String userEmail;

    private Long userMannerTemperature;

    private String userUnivName;
}
