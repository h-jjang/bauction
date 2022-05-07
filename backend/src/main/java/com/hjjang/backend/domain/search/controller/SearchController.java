package com.hjjang.backend.domain.search.controller;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.search.service.SearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

	private final SearchServiceImpl searchService;

	@GetMapping("/all")
	public ResponseEntity<Page<Post>> getAllPosts(Pageable pageable) {
		Page<Post> posts = searchService.findAll(pageable);
		return ResponseEntity.ok(posts);
	}

	@GetMapping
	public ResponseEntity<Page<Post>> searchPostsByKeyword(@RequestParam String keyword, Pageable pageable) {
		Page<Post> posts = searchService.findByKeyword(keyword, pageable);
		return ResponseEntity.ok(posts);
	}
}
