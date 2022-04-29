package com.hjjang.backend.domain.search.service;

import com.hjjang.backend.domain.search.dto.PostResponse;

public interface SearchService {

	PostResponse findAll();

	PostResponse findByKeyword(String keyword);
}
