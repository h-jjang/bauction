package com.hjjang.backend.domain.category.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.hjjang.backend.domain.category.dto.CategoryRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "removed")
	private Boolean removed = false;

	@Builder
	public Category(String name) {
		this.name = name;
	}

	public void updateCategory(CategoryRequest categoryRequest) {
		this.name = categoryRequest.getName();
	}

	public void removeCategory() {
		this.removed = true;
	}
}
