package com.hjjang.backend.domain.search.controller;

import com.hjjang.backend.domain.search.dto.PostResponse;
import com.hjjang.backend.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

	private final SearchService searchService;

	@GetMapping
	public ResponseEntity<PostResponse> getAllPosts() {
		PostResponse posts = searchService.findAll();
		return ResponseEntity.ok(posts);
	}
}
