package com.hjjang.backend.domain.image.service;

import com.hjjang.backend.domain.image.component.ImageUploader;
import com.hjjang.backend.domain.image.component.LocalUploader;
import com.hjjang.backend.domain.image.domain.entity.Image;
import com.hjjang.backend.domain.image.domain.repository.ImageRepository;
import com.hjjang.backend.domain.image.domain.repository.JpaImageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageUploader imageUploader;

    public ImageService(JpaImageRepository imageRepository, LocalUploader imageUploader) {
        this.imageRepository = imageRepository;
        this.imageUploader = imageUploader;
    }

    public Long save(Image image) {
        imageRepository.save(image);
        return image.getId();
    }
    
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
