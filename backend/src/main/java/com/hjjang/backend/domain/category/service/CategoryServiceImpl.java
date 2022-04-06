package com.hjjang.backend.domain.category.service;

import com.hjjang.backend.domain.category.domain.entity.Category;
import com.hjjang.backend.domain.category.domain.repository.CategoryRepository;
import com.hjjang.backend.domain.category.dto.CategoryRequest;
import com.hjjang.backend.domain.category.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void createNewCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequest.toEntity();
        categoryRepository.save(category);
    }

    @Override
    public Category findAllByCategory() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public void updateCategory(Category category, CategoryRequest categoryRequest) {
        category.updateCategory(categoryRequest);
        categoryRepository.save(category);
    }

    @Override
    public void removeCategory(Category category) {
        category.removeCategory();
        categoryRepository.save(category);
    }
}
