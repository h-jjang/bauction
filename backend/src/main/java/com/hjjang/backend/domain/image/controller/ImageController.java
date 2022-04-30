package com.hjjang.backend.domain.image.controller;

import com.hjjang.backend.domain.image.domain.entity.Image;
import com.hjjang.backend.domain.image.dto.ImagePathResponse;
import com.hjjang.backend.domain.image.service.LocalImageService;
import com.hjjang.backend.domain.image.service.S3ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.hjjang.backend.global.util.HttpStatusResponseEntity.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final LocalImageService imageService;

    @GetMapping()
    public ResponseEntity<List<ImagePathResponse>> findAllImage() {
        return ResponseEntity.ok(imageService
                .findAll()
                .stream()
                .map(ImagePathResponse::of)
                .collect(Collectors.toList())
        );
    }

    @PostMapping()
    public ResponseEntity<ImagePathResponse> uploadImage(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(ImagePathResponse
                .of(imageService
                        .uploadNewImage(multipartFile)
                )
        );
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<ImagePathResponse> findImage(@PathVariable Long imageId) {
        return ResponseEntity.ok(ImagePathResponse
                .of(imageService
                        .findImageById(imageId)
                )
        );
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<HttpStatus> deleteIMage(@PathVariable Long imageId) {
        Image image = imageService.findImageById(imageId);
        imageService.removeImage(image);

        return RESPONSE_OK;
    }
}
