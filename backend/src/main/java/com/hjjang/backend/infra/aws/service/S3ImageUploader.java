package com.hjjang.backend.infra.aws.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hjjang.backend.domain.image.service.ImageUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3ImageUploader extends ImageUploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket; // S3 버킷 이름

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        File uploadFile =
                this.localUpload(multipartFile) // 파일 변환할 수 없으면 에러
                        .orElseThrow(
                                () -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));

        return upload(uploadFile);
    }

    // S3로 파일 업로드하기
    private String upload(File uploadFile) {
        String uploadImageUrl = putS3(uploadFile, "static/" + uploadFile.getName()); // s3로 업로드
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }
}
