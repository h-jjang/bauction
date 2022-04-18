package com.hjjang.backend.global.config.security.service;

import java.util.Optional;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.hjjang.backend.domain.user.entity.Agreement;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.repository.UserRepository;
import com.hjjang.backend.global.config.security.parser.KakaoParsingParsingUserContext;
import com.hjjang.backend.global.config.security.parser.ParsingUserContext;
import com.hjjang.backend.global.config.security.principal.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ParsingUserContext userInfo = new KakaoParsingParsingUserContext(user.getAttributes());
        Optional<User> optionalSavedUser = userRepository.findUserById(userInfo.getId());
        User savedUser = null;
        if (optionalSavedUser.isPresent()) {
            savedUser = optionalSavedUser.get();
        }

        if (savedUser == null) {
            savedUser = createUser(userInfo);
        }

        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    private User createUser(ParsingUserContext userInfo) {
        User user = User.builder()
            .id(userInfo.getId())
            .nickName(userInfo.getName())
            .email(userInfo.getEmail())
            .isPushAgree(Agreement.DISAGREE)
            .mannerTemperature((long)36.5)
            .imageUrl(userInfo.getImageUrl())
            .role(RoleType.USER)
            .build();
        return userRepository.saveAndFlush(user);
    }
}
