package com.hjjang.backend.domain.category.controller;

import static com.hjjang.backend.global.util.HttpStatusResponseEntity.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hjjang.backend.domain.category.domain.entity.Category;
import com.hjjang.backend.domain.category.dto.CategoryRequest;
import com.hjjang.backend.domain.category.dto.CategoryResponse;
import com.hjjang.backend.domain.category.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryResponse>> findAllCategory() {

		return ResponseEntity.ok(categoryService
			.findAll()
			.stream()
			.map(CategoryResponse::of)
			.collect(Collectors.toList())
		);
	}

	@PostMapping
	public ResponseEntity<HttpStatus> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
		categoryService.createNewCategory(categoryRequest);

		return RESPONSE_OK;
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryResponse> findCategory(@PathVariable Long categoryId) {
		return ResponseEntity.ok(CategoryResponse.of(categoryService.findCategoryById(categoryId)));
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<HttpStatus> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest,
		@PathVariable Long categoryId) {
		Category category = categoryService.findCategoryById(categoryId);
		categoryService.updateCategory(category, categoryRequest);
		return RESPONSE_OK;
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long categoryId) {
		Category category = categoryService.findCategoryById(categoryId);

		categoryService.removeCategory(category);

		return RESPONSE_OK;
	}
}
