package com.hjjang.backend.domain.post.dto;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.user.entity.Agreement;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static com.hjjang.backend.domain.post.domain.entity.PostDefaultValue.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PostMapperTest {

    @InjectMocks
    private PostMapper postMapper;

    private Post expectPost;

    private User givenUser;

    private final String givenTitle = "test title";
    private final String givenContent = "test content";
    private final int givenItemPrice = 10000;

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

        expectPost = Post.builder()
                .user(givenUser)
                .title(givenTitle)
                .content(givenContent)
                .itemPrice(givenItemPrice)
                .build();

    }

    @Test
    @DisplayName("PostRequestDto -> Post 변환 테스트")
    void toEntityTest() {
        // given
        PostRequestDto givenPostRequestDto = PostRequestDto.builder()
                .title(givenTitle)
                .content(givenContent)
                .price(givenItemPrice)
                .build();
        // when
        Post actualPost = postMapper.toEntity(givenPostRequestDto, givenUser);
        // then
        assertAll(
                () -> assertEquals(expectPost.getUser(), actualPost.getUser()),
                () -> assertEquals(expectPost.getTitle(), actualPost.getTitle()),
                () -> assertEquals(expectPost.getContent(), actualPost.getContent()),
                () -> assertEquals(expectPost.getItemPrice(), actualPost.getItemPrice())
        );
    }

    @Test
    @DisplayName("Post -> PostResponseDto 변환 테스트")
    void fromEntityTest() {
        // given
        Post givenPost = new Post(1L, givenUser, givenTitle, givenContent, givenItemPrice,
                DEFAULT_VIEWS, DEFAULT_INTEREST_NUMBER, DEFAULT_CHAT_NUMBER,
                DEFAULT_IS_SALE_COMPLETION, DEFAULT_REMOVED, LocalDateTime.now());

        PostResponseDto expectPostResponseDto = PostResponseDto.builder()
                .id(givenPost.getId())
                .user_id(givenPost.getUser().getId())
                .title(givenPost.getTitle())
                .content(givenPost.getContent())
                .item_price(givenPost.getItemPrice())
                .views(givenPost.getViews())
                .interest_number(givenPost.getInterestNumber())
                .chat_number(givenPost.getChatNumber())
                .is_sale_completion(givenPost.getIsSaleCompletion().getState())
                .removed(givenPost.isRemoved())
                .time(givenPost.getTime().toString())
                .build();
        // when
        PostResponseDto actualPostResponseDto = postMapper.fromEntity(givenPost);
        // then
        Assertions.assertThat(actualPostResponseDto).isEqualToComparingFieldByField(expectPostResponseDto);
    }
}