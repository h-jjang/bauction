package com.hjjang.backend.domain.search.controller;

import com.hjjang.backend.domain.search.dto.PostResponse;
import com.hjjang.backend.domain.search.service.SearchServiceImpl;
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
	public ResponseEntity<PostResponse> getAllPosts() {
		PostResponse posts = searchService.findAll();
		return ResponseEntity.ok(posts);
	}

	@GetMapping
	public ResponseEntity<PostResponse> searchPostsByKeyword(@RequestParam String keyword) {
		PostResponse posts = searchService.findByKeyword(keyword);
		return ResponseEntity.ok(posts);
	}
}
