package com.agripulse.agripulse.services;

import com.agripulse.agripulse.dto.ImageResponseDto;
import com.agripulse.agripulse.dto.PaginatedResponse;
import com.agripulse.agripulse.exceptions.FileNotFoundException;
import com.agripulse.agripulse.exceptions.FileNotUploadedException;
import com.agripulse.agripulse.exceptions.ImageNotFoundException;
import com.agripulse.agripulse.exceptions.ImageNotSavedException;
import com.agripulse.agripulse.models.Image;

import java.util.List;
import java.util.UUID;

public interface ImageService {

    Image save(Image image) throws ImageNotSavedException;

    Image findById(UUID id) throws ImageNotFoundException;

    PaginatedResponse<ImageResponseDto> getAllImages(int pageNumber, int pageSize);

    List<Image> findByPostId(UUID postId) throws ImageNotFoundException;

    void deleteByImageId(UUID id) throws ImageNotFoundException, FileNotFoundException;

    void deleteByPostId(UUID id) throws ImageNotFoundException, FileNotFoundException;

}
