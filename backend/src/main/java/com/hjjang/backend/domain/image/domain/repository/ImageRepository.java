package com.hjjang.backend.domain.image.domain.repository;


import com.hjjang.backend.domain.image.domain.entity.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    Image save(Image image);

    Optional<Image> findById(Long id);

    List<Image> findAll();

}
