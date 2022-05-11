package com.hjjang.backend.domain.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjjang.backend.domain.category.domain.entity.Category;
import com.hjjang.backend.domain.category.domain.repository.CategoryRepository;
import com.hjjang.backend.domain.category.dto.CategoryRequest;
import com.hjjang.backend.domain.category.exception.CategoryNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	@Transactional
	public Long createNewCategory(CategoryRequest categoryRequest) {
		Category category = categoryRequest.toEntity();
		categoryRepository.save(category);
		return categoryRepository.save(category).getId();
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Category findCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
	}

	@Override
	public Category updateCategory(Category category, CategoryRequest categoryRequest) {
		category.updateCategory(categoryRequest);

		return categoryRepository.save(category);
	}

	@Override
	public void removeCategory(Category category) {
		category.removeCategory();
		categoryRepository.save(category);
	}
}
