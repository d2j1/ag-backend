package com.agripulse.agripulse.services;

import com.agripulse.agripulse.dto.ImageResponseDto;
import com.agripulse.agripulse.dto.PaginatedResponse;
import com.agripulse.agripulse.dto.PostResponseDto;
import com.agripulse.agripulse.exceptions.ImageNotFoundException;
import com.agripulse.agripulse.exceptions.ImageNotSavedException;
import com.agripulse.agripulse.exceptions.PostNotCreatedException;
import com.agripulse.agripulse.models.Image;
import com.agripulse.agripulse.repositories.ImageRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Override
    public Image save(Image image) throws ImageNotSavedException {
        try{
            return imageRepository.save(image);
        }catch (DataIntegrityViolationException e){
            // invalid data in post like nulls
            throw new ImageNotSavedException("Could not save the image. Please try again!");
        }catch (DataAccessException e){
            // not able to access the database
            throw new ImageNotSavedException("Could not save the image due to system unavailability. Please try after some time!");
        }catch (Exception e){
            throw new ImageNotSavedException("Could not save the image. Please try again after sometime!");
        }
    }

    @Override
    public Image findById(UUID id) throws ImageNotFoundException {

        Optional<Image> imageOptional = imageRepository.findById(id);

        if(imageOptional.isEmpty()){
            throw new ImageNotFoundException("Image not found for "+id);
        }
        return imageOptional.get();
    }

    @Override
    public List<Image> findAll() {
        return List.of();
    }

//    @Override
//    public PaginatedResponse<ImageResponseDto> findAll() {
//
//        List<Image> images = imageRepository.findAll();
//
//        HashMap<UUID, List<String>> postIdAndImages = new HashMap<>();
//
//        for(Image image : images){
//            List<String> urls = postIdAndImages.getOrDefault(image.getPost_id(), new ArrayList<String>());
//
//            urls.add(image.getImageUrl());
//
//            postIdAndImages.put(image.getPost_id(), urls);
//        }
//
//
//
//
//        return List.of();
//    }

    @Override
    public List<Image> findByPostId(UUID postId) throws ImageNotFoundException {
        List<Image> images = imageRepository.findByPostId(postId);

        if(images.isEmpty()){
            throw new ImageNotFoundException("Images not found for the provided post id: "+postId);
        }

        return images;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Image update(UUID id, Image image) {
        return null;
    }
}
