package com.hjjang.backend.infra.image.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    private Boolean removed = false;

    @Builder
    public Image(String path) {
        this.path = path;
    }

    public void removeImage() {
        this.removed = true;
    }
}
