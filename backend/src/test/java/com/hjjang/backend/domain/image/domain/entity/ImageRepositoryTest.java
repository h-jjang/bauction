package com.hjjang.backend.domain.image.domain.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hjjang.backend.domain.image.domain.repository.ImageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    void ImageTest() throws JsonProcessingException {
        //given
        Image image = new Image(0L, "test path", false);
        Image image1 = imageRepository.save(image);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String imageStr = objectMapper.writeValueAsString(image);
//        System.out.println(imageStr);
        Long imageId = image1.getId();

        //when
        Optional<Image> image2 = imageRepository.findById(imageId);

        //then1
        Assertions.assertEquals(image2, image1);

        //then2
        image.removeImage();
        Assertions.assertEquals(image2.get().getPath(), "test path");
        Assertions.assertEquals(image2.get().getRemoved(), true);
    }

}

/*
TODO

엔티티
    디비에 잘 들어가는지
DTO
    생성 잘 되는지
컴포넌트
    메소드별로 잘 동작하는지
컨트롤러
    가짜 요청을 잘 처리하는지
서비스
    메소드별로 의도대로 잘 작동하는지

 */