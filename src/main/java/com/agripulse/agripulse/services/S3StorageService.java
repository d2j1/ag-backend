package com.agripulse.agripulse.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3StorageService {
    String uploadFile(MultipartFile file) throws IOException;
    byte[] getFile(String key);

}
