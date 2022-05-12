package com.hjjang.backend.domain.post.domain.repository;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.user.entity.Agreement;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static com.hjjang.backend.domain.post.domain.entity.PostDefaultValue.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Post Repository 테스트")
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private Post expectPost1;

    private Post expectPost2;

    private Post givenPost1;
    private Post givenPost2;

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

        User actualUser = userRepository.save(givenUser);

        givenPost1 = Post.builder()
                .user(actualUser)
                .title(givenTitle)
                .content(givenContent)
                .itemPrice(givenItemPrice)
                .build();

        givenPost2 = Post.builder()
                .user(actualUser)
                .title(givenTitle)
                .content(givenContent)
                .itemPrice(givenItemPrice)
                .build();

        expectPost1 = postRepository.save(givenPost1);
        expectPost2 = postRepository.save(givenPost2);
    }

    @Test
    @DisplayName("같은 정보의 Post가 저장되는지 테스트")
    void saveSameTest() {
        // given

        // when
        Post actualPost = postRepository.save(givenPost2);
        // then
        assertAll(
                () -> assertEquals(givenPost2.getUser(), actualPost.getUser()),
                () -> assertEquals(givenPost2.getTitle(), actualPost.getTitle()),
                () -> assertEquals(givenPost2.getContent(), actualPost.getContent())
        );
    }

    @Test
    @DisplayName("빈 Post를 저장시킬 때 검증 절차에의해 에러가 발생하는지 테스트")
    void saveEmptyPostTest() {
        // given
        Post emptyPost = Post.builder().build();
        // when

        // then
        assertThrowsExactly(DataIntegrityViolationException.class, () -> postRepository.save(emptyPost));
    }

    @Test
    @DisplayName("Post의 기본 값이 잘 설정되는지 테스트")
    void saveDefaultValueTest() {
        // given

        // when
        Post actualPost = postRepository.save(givenPost1);
        // then
        assertAll(
                () -> assertEquals(DEFAULT_VIEWS, actualPost.getViews()),
                () -> assertEquals(DEFAULT_INTEREST_NUMBER, actualPost.getInterestNumber()),
                () -> assertEquals(DEFAULT_CHAT_NUMBER, actualPost.getChatNumber()),
                () -> assertEquals(DEFAULT_IS_SALE_COMPLETION, actualPost.getIsSaleCompletion()),
                () -> assertEquals(DEFAULT_REMOVED, actualPost.isRemoved())
        );
    }

    @Test
    @DisplayName("")
    void findAllTest() {
        // given
        List<Post> expectPostList = List.of(expectPost1, expectPost2);
        // when
        List<Post> actualPostList = postRepository.findAll();
        // then
        assertIterableEquals(expectPostList, actualPostList);
    }

    @Test
    @DisplayName("존재하는 ID로 죄회할 때 제대로 가져오는지 테스트")
    void findByActualIdTest() {
        // given

        // when
        Post actualPost = postRepository.getById(expectPost1.getId());
        // then
        assertEquals(expectPost1, actualPost);
    }

    @Test
    @DisplayName("존재하지 않는 ID로 조회할 때 Optional.empty()로 처리 되는지 테스트")
    void findByNotRealIdTest() {
        // given
        long givenId = 9999999L;
        // when
        // then
        assertEquals(Optional.empty(), postRepository.findById(givenId));
    }
}