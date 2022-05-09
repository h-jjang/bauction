package com.hjjang.backend.category;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hjjang.backend.domain.category.domain.entity.Category;
import com.hjjang.backend.domain.category.domain.repository.CategoryRepository;
import com.hjjang.backend.domain.category.dto.CategoryRequest;
import com.hjjang.backend.domain.category.exception.CategoryNotFoundException;
import com.hjjang.backend.domain.category.service.CategoryService;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
	@Mock
	private CategoryService categoryService;
	@Mock
	private CategoryRepository categoryRepository;

	private Category givenCategory;

	@BeforeEach
	void setUp(){
		givenCategory = Category.builder()
			.name("카테고리")
			.build();

		givenCategory.setId(1L);

		when(categoryRepository.getById(any())).thenReturn(givenCategory);
		when(categoryRepository.save(any())).thenReturn(givenCategory);
	}

	@DisplayName("카테고리 등록 기능")
	@Test
	void 카테고리_등록하기(){
		//given
		//when
		CategoryRequest categoryRequest = CategoryRequest.builder()
			.name("카테고리")
			.build();
		categoryService.createNewCategory(categoryRequest);
		//then
		assertAll(() -> assertEquals(categoryRepository.getById(1L), givenCategory)
		);
	}
	@DisplayName("카테고리 전체 조회 기능")
	@Test
	void 카테고리_전체_조회(){
		//given
		String name = "가전제품";
		CategoryRequest categoryRequest = new CategoryRequest(name);
		Category category = categoryRequest.toEntity();
		categoryRepository.save(category);

		//when
		List<Category> categoryList = categoryService.findAll();
		//then
		List<Category> categoryListRepository = categoryRepository.findAll();
		assertThat(categoryList).isEqualTo(categoryListRepository);
	}
	@DisplayName("특정 카테고리 조회")
	@Test
	void 특정_카테고리_조회(){
		//given
		Category category = Category.builder()
			.name("가전 제품")
			.build();
		categoryRepository.save(category);
		//when
		System.out.println(categoryService.findCategoryById(category.getId()));
		//then
		assertThat(category.getId()).isEqualTo(categoryRepository.findById(category.getId()).orElseThrow(
			CategoryNotFoundException::new));

	}

}
