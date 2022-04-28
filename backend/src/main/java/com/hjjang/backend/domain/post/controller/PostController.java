package com.hjjang.backend.domain.post.controller;

import com.hjjang.backend.domain.post.dto.PostRequest;
import com.hjjang.backend.domain.post.dto.PostResponse;
import com.hjjang.backend.domain.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/items")
@RestController
public class PostController {

    private final PostServiceImpl itemService;

    @PostMapping
    public ResponseEntity<PostResponse> createItem(@Validated @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(PostResponse
                .of(itemService
                        .save(postRequest
                                .toEntity()
                        )
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAllItem() {
        return ResponseEntity.ok(itemService
                .findAll()
                .stream()
                .map(PostResponse::of)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findOneItem(@PathVariable Long id) {
        return ResponseEntity.ok(PostResponse.of(itemService.findOneById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOneItem(@PathVariable Long id) {
        itemService.deleteOneById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


}
