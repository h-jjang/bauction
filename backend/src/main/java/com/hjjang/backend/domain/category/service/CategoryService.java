package com.hjjang.backend.domain.category.service;

import com.hjjang.backend.domain.category.domain.entity.Category;
import com.hjjang.backend.domain.category.dto.CategoryRequest;

public interface CategoryService {

	public Category findAllByCategory();

	public Category findCategoryById(Long categoryId);

	public void createNewCategory(CategoryRequest categoryRequest);

	public void updateCategory(Category category, CategoryRequest categoryRequest);

	public void removeCategory(Category category);

}
