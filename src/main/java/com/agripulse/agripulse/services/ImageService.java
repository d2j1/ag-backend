package com.agripulse.agripulse.services;

import com.agripulse.agripulse.exceptions.ImageNotFoundException;
import com.agripulse.agripulse.exceptions.ImageNotSavedException;
import com.agripulse.agripulse.models.Image;

import java.util.List;
import java.util.UUID;

public interface ImageService {


    Image save(Image image) throws ImageNotSavedException;

    Image findById(UUID id) throws ImageNotFoundException;
    List<Image> findAll();
    List<Image> findByPostId(UUID postId);

    void delete(UUID id);
    Image update(UUID id, Image image);


}
