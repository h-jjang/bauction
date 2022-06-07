package com.hjjang.backend.domain.search.controller;

import com.hjjang.backend.domain.post.dto.PostMapper;
import com.hjjang.backend.domain.search.service.SearchServiceImpl;
import com.hjjang.backend.global.dto.ApiResponse;
import com.hjjang.backend.global.util.UserUtil;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/searches")
public class SearchController {

	private final SearchServiceImpl searchService;
	private final PostMapper postMapper;
	private final UserUtil userUtil;

	@GetMapping("/all")
	public ResponseEntity<ApiResponse> searchPosts(
		@RequestParam(required = false) String filter,
		@PageableDefault(sort = "time", direction = Direction.DESC) Pageable pageable) {
		return ResponseEntity.ok(
			ApiResponse.success(
			"searchPosts",
				searchService.findAll(filter, pageable, userUtil.getLoginUserByToken())
				.stream()
				.map(postMapper::fromEntity)
				.collect(Collectors.toList()))
		);
	}

	@GetMapping
	public ResponseEntity<ApiResponse> searchPostsByKeyword(
		@RequestParam String keyword,
		@RequestParam(required = false) String filter,
		@PageableDefault(sort = "time", direction = Direction.DESC) Pageable pageable) {
		return ResponseEntity.ok(
			ApiResponse.success(
				"searchPostsByKeyword",
				searchService.findByKeyword(keyword, filter, pageable, userUtil.getLoginUserByToken())
					.stream()
					.map(postMapper::fromEntity)
					.collect(Collectors.toList())
			)
		);
	}
}
