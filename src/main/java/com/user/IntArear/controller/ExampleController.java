package com.user.IntArear.controller;

import com.user.IntArear.util.S3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/example")
@RequiredArgsConstructor
public class ExampleController {

    @Value("${s3.bucket_name}")
    private String bucketName;

    private final S3Util s3Util;

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("uploadFile") MultipartFile file) {

        log.info("bucketName : {}", bucketName);
        s3Util.uploadFileToS3("https://" + bucketName + ".s3.ap-northeast-2.amazonaws.com/" + file.getName(), file);

        return ResponseEntity.ok().build();
    }
}
