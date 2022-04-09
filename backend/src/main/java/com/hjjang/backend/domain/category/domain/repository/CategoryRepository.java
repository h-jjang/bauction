package com.hjjang.backend.domain.category.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hjjang.backend.domain.category.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
