package com.hjjang.backend.domain.image.service;

import com.hjjang.backend.domain.image.domain.repository.ImageRepository;
import com.hjjang.backend.domain.image.exception.ImageNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @InjectMocks
    private ImageServiceImpl imageService;

    @Mock
    private ImageRepository imageRepository;

    @Test
    void uploadNewImage() {

    }

    @Test
    @DisplayName("존재하지 않는 이미지로 검색을 하였을 경우 ImageNotFoundException 발생한다.")
    void nothingFoundImageById() {
        //given
        when(imageRepository.findById(anyLong())).thenReturn(Optional.empty());

        //then
        assertThrows(ImageNotFoundException.class, () -> {
            imageService.findImageById(anyLong());
        });
    }
}