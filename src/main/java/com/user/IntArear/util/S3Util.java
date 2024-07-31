package com.user.IntArear.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

@Component
@RequiredArgsConstructor
public class S3Util {

    @Value("${s3.bucket_name}")
    private String dataBucketName;

    private final S3Client s3Client;

    public void uploadFileToS3(String s3Path, MultipartFile multipartFile) {
        File file = (File) multipartFile;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(dataBucketName)
                .key(s3Path)
                .acl(ObjectCannedACL.BUCKET_OWNER_FULL_CONTROL)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
    }
}
