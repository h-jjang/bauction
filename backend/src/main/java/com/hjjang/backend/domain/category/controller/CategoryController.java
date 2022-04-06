package com.hjjang.backend.domain.category.controller;

import com.hjjang.backend.domain.category.domain.entity.Category;
import com.hjjang.backend.domain.category.dto.CategoryRequest;
import com.hjjang.backend.domain.category.dto.CategoryResponse;
import com.hjjang.backend.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.hjjang.backend.global.util.HttpStatusResponseEntity.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

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
    public ResponseEntity<HttpStatus> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable Long categoryId) {
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
