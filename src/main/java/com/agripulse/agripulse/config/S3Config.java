package com.agripulse.agripulse.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;


import java.net.URI;

@Configuration
public class S3Config {

    @Value("${cloud.aws.s3.endpoint}")
    private String endpoint;

    @Value("${cloud.aws.region}")
    private String region;

    @Value("${cloud.aws.s3.credentials.access-key")
    private String accessKey;

    @Value("${cloud.aws.s3.credentials.secret-key")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket")
    private String bucketName;


    @Bean
    public S3Client s3Client() {
        S3ClientBuilder builder = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)
                        )
                );

        if (endpoint != null && !endpoint.isBlank() && endpoint.contains("localhost")) {
            builder.endpointOverride(URI.create(endpoint))
                    .serviceConfiguration(
                            S3Configuration.builder()
                                    .pathStyleAccessEnabled(true)
                                    .build()
                    );
        }

        return builder.build();
    }

//    // Inject the S3Client bean here
//    private final S3Client s3Client;
//
//    public S3Config(S3Client s3Client) {
//        this.s3Client = s3Client;
//    }
//
//    @PostConstruct
//    public void createBucketIfNotExists() {
//        try {
//            s3Client.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());
//            System.out.println("Bucket already exists: " + bucketName);
//        } catch (S3Exception e) {
//            if (e.statusCode() == 404) {
//                s3Client.createBucket(CreateBucketRequest.builder()
//                        .bucket(bucketName)
//                        .build());
//                System.out.println("✅ Bucket created: " + bucketName);
//            } else {
//                throw e;
//            }
//        }
//    }

//    @Bean
//    public S3Client s3Client() {
//        return S3Client.builder()
//                .endpointOverride(URI.create(endpoint))
//                .region(Region.of(region))
//                .credentialsProvider(
//                        StaticCredentialsProvider.create(
//                                AwsBasicCredentials.create(accessKey, secretKey)
//                        )
//                )
//                .build();
//    }


}
