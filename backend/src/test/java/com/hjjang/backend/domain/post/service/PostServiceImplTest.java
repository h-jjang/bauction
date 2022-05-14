package com.hjjang.backend.domain.post.service;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.post.domain.repository.PostRepository;
import com.hjjang.backend.domain.post.dto.PostRequestDto;
import com.hjjang.backend.domain.post.exception.PostNotFoundException;
import com.hjjang.backend.domain.user.entity.Agreement;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.exception.UserNotMatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Post 서비스 테스트")
class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    private Post expectPost;

    private User givenUser;

    private List<Post> expectPostList = new ArrayList<>();

    private final long givenInvalidId = 0L;
    private final long givenValidId = 1L;

    @BeforeEach
    void setUp() {
        givenUser = User.builder()
                .providerId("test Id")
                .email("user@email.ac.kr")
                .imageUrl("test path")
                .isPushAgree(Agreement.AGREE)
                .mannerTemperature(36L)
                .nickName("test NN")
                .role(RoleType.USER)
                .univId(1L)
                .build();

        String givenTitle = "test title";
        String givenContent = "test content";
        int givenItemPrice = 10000;

        expectPost = Post.builder()
                .user(givenUser)
                .title(givenTitle)
                .content(givenContent)
                .itemPrice(givenItemPrice)
                .build();

        expectPostList = List.of(expectPost);

        when(postRepository.save(any())).thenReturn(expectPost);
        when(postRepository.findAll()).thenReturn(expectPostList);
        when(postRepository.findById(givenInvalidId)).thenReturn(Optional.empty());
        when(postRepository.findById(givenValidId)).thenReturn(Optional.ofNullable(expectPost));
    }

    @Test
    @DisplayName("Post 저장 테스트")
    void saveTest() {
        // given

        // when
        Post actualPost = postService.save(expectPost);
        // then
        assertEquals(expectPost, actualPost);
    }

    @Test
    @DisplayName("Post 리스트를 잘 불러오는지 테스트")
    void findAllTest() {
        // given

        // when
        List<Post> actualPostList = postService.findAll();
        // then
        assertIterableEquals(expectPostList, actualPostList);
    }

    @Test
    @DisplayName("존재하는 Id로 검색을 요청할 때 제대로 불러오는지 테스트")
    void findOneByValidIdTest() {
        // given

        // when
        Post actualPost = postService.findOneById(givenValidId);
        // then
        assertEquals(expectPost, actualPost);
    }

    @Test
    @DisplayName("존재하지 않는 Id로 검색을 요청할 때 제대로 예외를 발생 시키는지 테스트")
    void findOneByInvalidIdTest() {
        // given

        // when

        // then
        assertThrowsExactly(PostNotFoundException.class, () -> postService.findOneById(givenInvalidId));
    }

    @Test
    @DisplayName("올바른 User 정보를 가질 때, Post 변경이 잘 반영되는지 테스트")
    void updateOneByIdWithValidUserTest() {
        // given
        String changedTitle = "changed title";
        String changedContent = "changed content";
        int changedPrice = 999999;

        PostRequestDto givenPostRequestDto = PostRequestDto.builder()
                .title(changedTitle)
                .content(changedContent)
                .price(changedPrice)
                .build();

        Post expectPost = Post.builder()
                .user(givenUser)
                .title(changedTitle)
                .content(changedTitle)
                .itemPrice(changedPrice)
                .build();

        when(postService.save(any())).thenReturn(expectPost);
        // when
        Post actualPost = postService.updateOneById(givenValidId, givenPostRequestDto, givenUser);
        // then
        assertEquals(expectPost, actualPost);
    }

    @Test
    @DisplayName("올바르지 않은 User 정보를 가질 때, 예외를 처리하는지 테스트")
    void updateOneByIdWithInvalidUserTest() {
        // given
        String changedTitle = "changed title";
        String changedContent = "changed content";
        int changedPrice = 999999;

        PostRequestDto givenPostRequestDto = PostRequestDto.builder()
                .title(changedTitle)
                .content(changedContent)
                .price(changedPrice)
                .build();

        User invalidUser = User.builder().build();
        // when

        // then
        assertThrowsExactly(UserNotMatchException.class, () -> postService.updateOneById(givenValidId, givenPostRequestDto, invalidUser));
    }

    @Test
    @DisplayName("삭제 처리에서 예외가 발생하지 않는지 테스트")
    void deleteOneByIdTest() {
        // given

        // when

        // then
        assertDoesNotThrow(() -> postService.deleteOneById(givenValidId));
    }
}