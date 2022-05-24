package com.hjjang.backend.domain.user.dto;

import com.hjjang.backend.domain.user.entity.User;
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

    public UserProfileDTO(User user) {
        this.userNickname = user.getNickName();
        this.userImageUrl = user.getImageUrl();
        this.userEmail = user.getEmail();
        this.userNickname = user.getNickName();
        //todo 산기대를 univName로 바꿀 것
        this.userUnivName = "산기대";
    }
}
