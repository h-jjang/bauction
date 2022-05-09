package com.hjjang.backend.domain.post.domain.entity;

import com.hjjang.backend.domain.post.dto.PostRequestDto;
import com.hjjang.backend.domain.user.entity.Agreement;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    private Post post;

    private final String givenTitle = "test title";
    private final String givenContent = "test content";
    private final int givenItemPrice = 10000;

    @BeforeEach
    void setUp() {
        User givenUser = User.builder()
                .providerId("test Id")
                .email("user@email.ac.kr")
                .imageUrl("test path")
                .isPushAgree(Agreement.AGREE)
                .mannerTemperature(36L)
                .nickName("test NN")
                .role(RoleType.USER)
                .univId(1L)
                .build();

        post = Post.builder()
                .user(givenUser)
                .title(givenTitle)
                .content(givenContent)
                .itemPrice(givenItemPrice)
                .build();
    }

    @Test
    @DisplayName("Post 삭제 처리 테스트")
    void removePostTest() {
        // given

        // when
        post.removePost();
        // then
        assertTrue(post.isRemoved());
    }

    @Test
    @DisplayName("Post 갱신 테스트")
    void updateTest() {
        // given
        PostRequestDto givenPostRequestDto = PostRequestDto.builder()
                .title(givenTitle)
                .content(givenContent)
                .price(givenItemPrice)
                .build();

        Post expectPost = Post.builder()
                .title(givenTitle)
                .content(givenContent)
                .itemPrice(givenItemPrice)
                .build();

        // when
        Post actualPost = post.update(givenPostRequestDto);
        // then
        assertAll(
                () -> assertEquals(expectPost.getTitle(), actualPost.getTitle()),
                () -> assertEquals(expectPost.getContent(), actualPost.getContent()),
                () -> assertEquals(expectPost.getItemPrice(), actualPost.getItemPrice())
        );
    }
}