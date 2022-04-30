package com.hjjang.backend.domain.image.service;

import com.hjjang.backend.domain.image.domain.repository.ImageRepository;
import com.hjjang.backend.domain.image.domain.entity.Image;
import com.hjjang.backend.domain.image.exception.ImageNotFoundException;
import com.hjjang.backend.infra.aws.service.S3ImageUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;

    private final S3ImageUploader imageUploader;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public Image findImageById(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
    }

    public Image uploadNewImage(MultipartFile multipartFile) throws IOException {
        return imageRepository.save(new Image(imageUploader.upload(multipartFile)));
    }

    public void removeImage(Image image) {
        image.removeImage();
        imageRepository.save(image);
    }

}
