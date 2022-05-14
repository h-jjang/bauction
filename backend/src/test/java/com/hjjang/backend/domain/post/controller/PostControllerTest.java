package com.hjjang.backend.domain.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.post.dto.PostMapper;
import com.hjjang.backend.domain.post.dto.PostRequestDto;
import com.hjjang.backend.domain.post.dto.PostResponseDto;
import com.hjjang.backend.domain.post.service.PostServiceImpl;
import com.hjjang.backend.domain.user.entity.Agreement;
import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.global.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hjjang.backend.domain.post.domain.entity.PostDefaultValue.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PostController.class)
//@ExtendWith(RestDocumentationExtension.class)
//@AutoConfigureRestDocs
class PostControllerTest {

    //TODO security 관련 종속성을 어떻게 해결해야 할지 모르겠음 아마도 MockBean 으로 만들고 모두 when then 을 설정하면 되지 않을까 싶은데 너무 비효율 적
    @MockBean private PostServiceImpl postService;
    @MockBean private PostMapper postMapper;
    @MockBean private UserUtil userUtil;
//    @MockBean private AuthProperties authProperties;
//    @MockBean private AuthTokenProvider authTokenProvider;
//    @MockBean private CustomOAuth2UserService customOAuth2UserService;
//    @MockBean private TokenAccessDeniedHandler tokenAccessDeniedHandler;
//    @MockBean private UserRefreshTokenRepository userRefreshTokenRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private final long givenId = 1L;

    private final String givenTitle = "test title";
    private final String givenContent = "test content";
    private final int givenItemPrice = 10000;

    private final String changedTitle = "changed Title";
    private final String changedContent = "changed Content";
    private final int changedItemPrice = 999999;

    private final String providerId = "provider Id";
    private final String nickName = "NN";
    private final String email = "user@email.ac.kr";
    private final long mannerTemperature = 36L;
    private final String imagePath = "image path";
    private final Agreement pushAgree = Agreement.AGREE;
    private final long univId = 1L;
    private final RoleType role = RoleType.USER;

    private final Map<String, String> givenPostRequestJson = new HashMap<>();

    private PostResponseDto expectPostResponseDto;

    private final Map<String, String> expectApiResponseJson = new HashMap<>();

    @BeforeEach
    void setUp(WebApplicationContext context) {
        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .build();

        givenPostRequestJson.put("title", givenTitle);
        givenPostRequestJson.put("content", givenContent);
        givenPostRequestJson.put("price", String.valueOf(givenItemPrice));

        User givenUser = User.builder()
                .providerId(providerId)
                .email(email)
                .imageUrl(imagePath)
                .isPushAgree(pushAgree)
                .mannerTemperature(mannerTemperature)
                .nickName(nickName)
                .role(role)
                .univId(univId)
                .build();

        Post expectRawPost = Post.builder()
                .user(givenUser)
                .title(givenTitle)
                .content(givenContent)
                .itemPrice(givenItemPrice)
                .build();

//        User expectSavedUser = new User(givenId, providerId, nickName, email,
//                mannerTemperature, imagePath, LocalDateTime.now(), pushAgree, univId, role);

        Post expectSavedPost = new Post(givenId, givenUser, givenTitle, givenContent, givenItemPrice,
                DEFAULT_VIEWS, DEFAULT_INTEREST_NUMBER, DEFAULT_CHAT_NUMBER,
                DEFAULT_IS_SALE_COMPLETION, DEFAULT_REMOVED, LocalDateTime.now());

        Post expectUpdatedPost = new Post(givenId, givenUser, changedTitle, changedContent,
                changedItemPrice, DEFAULT_VIEWS, DEFAULT_INTEREST_NUMBER, DEFAULT_CHAT_NUMBER,
                DEFAULT_IS_SALE_COMPLETION, DEFAULT_REMOVED, LocalDateTime.now());

        expectPostResponseDto = PostResponseDto.builder()
                .id(expectSavedPost.getId())
                .user_id(expectSavedPost.getUser().getId())
                .title(expectSavedPost.getTitle())
                .content(expectSavedPost.getContent())
                .item_price(expectSavedPost.getItemPrice())
                .views(expectSavedPost.getViews())
                .interest_number(expectSavedPost.getInterestNumber())
                .chat_number(expectSavedPost.getChatNumber())
                .is_sale_completion(expectSavedPost.getIsSaleCompletion().getState())
                .removed(expectSavedPost.isRemoved())
                .time(expectSavedPost.getTime().toString())
                .build();

        expectApiResponseJson.put("id", String.valueOf(givenId));
        expectApiResponseJson.put("user_id", String.valueOf(givenId));
        expectApiResponseJson.put("title", givenTitle);
        expectApiResponseJson.put("content", givenContent);
        expectApiResponseJson.put("item_price", String.valueOf(givenItemPrice));
        expectApiResponseJson.put("views", String.valueOf(DEFAULT_VIEWS));
        expectApiResponseJson.put("interest_number", String.valueOf(DEFAULT_INTEREST_NUMBER));
        expectApiResponseJson.put("chat_number", String.valueOf(DEFAULT_CHAT_NUMBER));
        expectApiResponseJson.put("removed", String.valueOf(DEFAULT_REMOVED));
        expectApiResponseJson.put("time", String.valueOf(LocalDateTime.now()));

        List<Post> expectPostList = List.of(expectSavedPost);

        when(postMapper.fromEntity(any())).thenReturn(expectPostResponseDto);
        when(postMapper.toEntity(any(), any())).thenReturn(expectRawPost);
        when(postService.save(expectRawPost)).thenReturn(expectSavedPost);
        when(postService.findAll()).thenReturn(expectPostList);
        when(postService.findOneById(anyLong())).thenReturn(expectSavedPost);
        when(postService.updateOneById(anyLong(), any(PostRequestDto.class), any(User.class)))
                .thenReturn(expectUpdatedPost);
        doNothing().when(postService).deleteOneById(anyLong());

    }

    @Test
    void createItemTest() throws Exception {
        //given
        Map<String, Object> expectResponseEntityJson = new HashMap<>();
        expectResponseEntityJson.put("name", "createItem");
        expectResponseEntityJson.put("body", expectApiResponseJson);
        //when

        //then
        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(givenPostRequestJson)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(expectResponseEntityJson)));
    }

    @Test
    void findAllItem() {
    }

    @Test
    void findOneItem() {
    }

    @Test
    void putOneItem() {
    }

    @Test
    void deleteOneItem() {
    }
}