package com.hjjang.backend.domain.category.domain.entity;

import com.hjjang.backend.domain.category.dto.CategoryRequest;
import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
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
