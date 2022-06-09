package com.hjjang.backend.domain.post.controller;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

import com.hjjang.backend.domain.mail.service.NoticeMailService;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.post.dto.PostMapper;
import com.hjjang.backend.domain.post.dto.PostRequestDto;
import com.hjjang.backend.domain.post.dto.PostResponseDto;
import com.hjjang.backend.domain.post.service.PostServiceImpl;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.global.dto.ApiResponse;
import com.hjjang.backend.global.response.code.SuccessCode;
import com.hjjang.backend.global.response.response.SuccessResponse;
import com.hjjang.backend.global.util.UserUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostServiceImpl postService;
    private final NoticeMailService mailService;
    private final PostMapper postMapper;
    private final UserUtil userUtil;

    @PostMapping
    public ResponseEntity<SuccessResponse> createItem(@Validated @RequestBody PostRequestDto postRequestDto) {
        User user = userUtil.getLoginUserByToken();
        Post post = postService.save(postMapper.toEntity(postRequestDto, user.getUniversity()));
        PostResponseDto postResponseDto = postMapper.fromEntity(post);
        mailService.sendNotice(user, post);
        return ResponseEntity.ok(SuccessResponse.of(SuccessCode.POST_CREATE_SUCCESS, postResponseDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllItem() {
        return ok(
                ApiResponse.success("findAllItem", postService
                        .findAll()
                        .stream()
                        .map(postMapper::fromEntity)
                        .collect(Collectors.toList())
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findOneItem(@PathVariable Long id) {
        return ok(ApiResponse.success("findOneItem", postMapper.fromEntity(postService.findOneById(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> putOneItem(@PathVariable Long id, @Validated @RequestBody PostRequestDto postRequestDto) {
        return status(CREATED)
                .body(ApiResponse.success(
                                "updatePutOneItem",
                                postMapper.fromEntity(
                                        postService.updateOneById(
                                                id, postRequestDto, userUtil.getLoginUserByToken()
                                        )
                                )
                        )
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOneItem(@PathVariable Long id) {
        postService.deleteOneById(id);
        return ok(ApiResponse.success("deleteOneItem", ACCEPTED));
    }
}
