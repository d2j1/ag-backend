package com.agripulse.agripulse.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

public class S3StorageServiceImpl implements S3StorageService{

    private final S3Client s3Client;
    private final String bucketName;
    private final String endpoint;


    public S3StorageServiceImpl(S3Client s3Client, @Value("${cloud.aws.s3.bucket}") String bucketName,  @Value("${cloud.aws.s3.endpoint}") String endpoint){
        this.bucketName = bucketName;
        this.s3Client = s3Client;
        this.endpoint = endpoint;
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String key = UUID.randomUUID() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

        // Return S3 URL (works for LocalStack and AWS)
        return endpointUrl() + "/" + key;
    }

    @Override
    public byte[] getFile(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
        return objectBytes.asByteArray();
    }


    private String endpointUrl() {
        return endpoint.replaceAll("/$", "") + "/" + bucketName;
    }
}
