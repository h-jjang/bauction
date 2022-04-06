package com.hjjang.backend.domain.category.domain.repository;

import com.hjjang.backend.domain.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
