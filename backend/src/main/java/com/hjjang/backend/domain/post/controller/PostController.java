package com.hjjang.backend.domain.post.controller;

import com.hjjang.backend.domain.post.dto.PostRequest;
import com.hjjang.backend.domain.post.dto.PostResponse;
import com.hjjang.backend.domain.post.service.PostServiceImpl;
import com.hjjang.backend.global.dto.ApiResponse;
import com.hjjang.backend.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostServiceImpl postService;
    private final UserUtil userUtil;

    @PostMapping
    public ResponseEntity<ApiResponse> createItem(@Validated @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(
                ApiResponse.success("createItem",
                        PostResponse.of(
                                postService.save(
                                        postRequest.toEntity(
                                                userUtil.getLoginUserByToken()
                                        )
                                )
                        )
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllItem() {
        return ResponseEntity.ok(
                ApiResponse.success("findAllItem", postService
                        .findAll()
                        .stream()
                        .map(PostResponse::of)
                        .collect(Collectors.toList())
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findOneItem(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("findOneItem", PostResponse.of(postService.findOneById(id))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOneItem(@PathVariable Long id) {
        postService.deleteOneById(id);
        return ResponseEntity.ok(ApiResponse.success("deleteOneItem", HttpStatus.ACCEPTED));
    }


}
