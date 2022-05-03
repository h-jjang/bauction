package com.hjjang.backend.user.repository;

import com.hjjang.backend.domain.user.entity.Agreement;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityExistsException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User givenUser;
    private User newUser;

    @BeforeEach
    void setUp() {
        givenUser = User.builder()
                .email("tester@tukorea.ac.kr")
                .imageUrl("이미지 url")
                .isPushAgree(Agreement.AGREE)
                .mannerTemperature(36L)
                .nickName("kevinkim")
                .providerId("123412512")
                .role(RoleType.USER)
                .univId(null)
                .build();

        newUser = userRepository.save(givenUser);
    }

    @DisplayName("userId로 사용자 조회")
    @Test
    public void findUserById_test() {
        System.out.println(newUser.getProviderId());
        User foundUser = userRepository.findUserById(newUser.getId()).orElseThrow(EntityExistsException::new);

        assertAll(
                () -> assertEquals(foundUser, newUser)
        );
    }

    @DisplayName("providerId로 사용자 조회")
    @Test
    public void findUserByProviderId_test() {

        User foundUser = userRepository.findUserByProviderId(newUser.getProviderId())
                .orElseThrow(EntityExistsException::new);

        assertAll(
                () -> assertEquals(foundUser, newUser)
        );
    }

}
