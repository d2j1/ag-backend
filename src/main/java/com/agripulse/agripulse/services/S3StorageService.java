package com.agripulse.agripulse.services;

import com.agripulse.agripulse.exceptions.FileNotUploadedException;
import com.agripulse.agripulse.exceptions.InvalidFileFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3StorageService {
    String uploadFile(MultipartFile file) throws IOException, FileNotUploadedException, InvalidFileFormatException;
    byte[] getFile(String key);

}
