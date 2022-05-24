package com.hjjang.backend.domain.post.controller;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

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

import com.hjjang.backend.domain.post.dto.PostMapper;
import com.hjjang.backend.domain.post.dto.PostRequestDto;
import com.hjjang.backend.domain.post.service.PostServiceImpl;
import com.hjjang.backend.global.dto.ApiResponse;
import com.hjjang.backend.global.util.UserUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostServiceImpl postService;
    private final PostMapper postMapper;
    private final UserUtil userUtil;

    @PostMapping
    public ResponseEntity<ApiResponse> createItem(@Validated @RequestBody PostRequestDto postRequestDto) {
        return status(CREATED)
                .body(ApiResponse.success(
                                "createItem",
                                postMapper.fromEntity(
                                        postService.save(
                                                postMapper.toEntity(
                                                        postRequestDto, userUtil.getLoginUserByToken().getUniversity()
                                                )
                                        )
                                )
                        )
                );
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
