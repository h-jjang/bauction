package com.hjjang.backend.domain.category.service;

import java.util.List;

import com.hjjang.backend.domain.category.domain.entity.Category;
import com.hjjang.backend.domain.category.dto.CategoryRequest;

public interface CategoryService {

	public List<Category> findAll();

	public Category findCategoryById(Long categoryId);

	public Long createNewCategory(CategoryRequest categoryRequest);

	public Category updateCategory(Category category, CategoryRequest categoryRequest);

	public void removeCategory(Category category);

}
