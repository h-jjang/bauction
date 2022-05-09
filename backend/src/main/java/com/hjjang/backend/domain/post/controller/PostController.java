package com.hjjang.backend.domain.post.controller;

import com.hjjang.backend.domain.post.dto.PostMapper;
import com.hjjang.backend.domain.post.dto.PostRequestDto;
import com.hjjang.backend.domain.post.dto.PostResponseDto;
import com.hjjang.backend.domain.post.service.PostServiceImpl;
import com.hjjang.backend.global.dto.ApiResponse;
import com.hjjang.backend.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

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
                                                        postRequestDto, userUtil.getLoginUserByToken()
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
