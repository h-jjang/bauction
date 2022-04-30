package com.hjjang.backend.infra.image.domain.repository;

import com.hjjang.backend.infra.image.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
