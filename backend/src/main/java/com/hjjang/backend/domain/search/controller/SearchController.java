package com.hjjang.backend.domain.search.controller;

import com.hjjang.backend.domain.post.domain.entity.Post;
import com.hjjang.backend.domain.search.service.SearchServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> posts = searchService.findAll();
		return ResponseEntity.ok(posts);
	}

	@GetMapping
	public ResponseEntity<List<Post>> searchPostsByKeyword(@RequestParam String keyword) {
		List<Post> posts = searchService.findByKeyword(keyword);
		return ResponseEntity.ok(posts);
	}
}
