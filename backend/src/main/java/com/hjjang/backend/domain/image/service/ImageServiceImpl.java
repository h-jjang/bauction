package com.hjjang.backend.domain.image.service;

import com.hjjang.backend.domain.image.component.ImageUploader;
import com.hjjang.backend.domain.image.component.LocalUploader;
import com.hjjang.backend.domain.image.domain.entity.Image;
import com.hjjang.backend.domain.image.domain.repository.ImageRepository;
import com.hjjang.backend.domain.image.exception.ImageNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService{
    private final ImageRepository imageRepository;
    private final ImageUploader imageUploader;

    public ImageServiceImpl(ImageRepository imageRepository, LocalUploader imageUploader) {
        this.imageRepository = imageRepository;
        this.imageUploader = imageUploader;
    }

    public Long save(Image image) {
        imageRepository.save(image);
        return image.getId();
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findImageById(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
    }

    @Override
    public void uploadNewImage(MultipartFile multipartFile) throws IOException {
        Image image = new Image(imageUploader.upload(multipartFile));
        imageRepository.save(image);
    }

    @Override
    public void removeImage(Image image) {
        image.removeImage();
        imageRepository.save(image);
    }
}
