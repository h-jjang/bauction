package com.hjjang.backend.domain.image.controller;

import com.hjjang.backend.domain.image.domain.entity.Image;
import com.hjjang.backend.domain.image.dto.ImageResponse;
import com.hjjang.backend.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.hjjang.backend.global.util.HttpStatusResponseEntity.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @GetMapping()
    public List<Image> findAllImage() {
        return imageService.findAll();
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> uploadImage(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        imageService.uploadNewImage(multipartFile);
        return RESPONSE_OK;
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImageResponse> findImage(@PathVariable Long imageId) {
        return ResponseEntity.ok(ImageResponse.of(imageService.findImageById(imageId)));
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<HttpStatus> deleteIMage(@PathVariable Long imageId) {
        Image image = imageService.findImageById(imageId);
        imageService.removeImage(image);

        return RESPONSE_OK;
    }
}
